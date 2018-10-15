package database.journal

import java.sql.Timestamp

import database.journal.PPersistentNode.NodeType.{Ii, Obj, Objs}
import database.EventSub
import types.Path
import types.odf.{BooleanValue, Description, DoubleValue, FloatValue, ImmutableODF, InfoItem, IntValue, LongValue, MetaData, ODFParser, ODFValue, Object, Objects, QlmID, ShortValue, Value}
import utils._

object Models {

  sealed trait PersistentMessage

  /**
    * Event is a type for Event classes generated by protobuf.
    * Event classes are located in O-MI-Node/target/scala-2.11/src_managed/main/database.journal/
    */
  trait Event extends PersistentMessage

  //trait PersistentNode
  trait PersistentSub extends PersistentMessage

  trait Command

  //PersistentCommands are classes that need to be serialized
  trait PersistentCommand extends Command with PersistentMessage

  case class SaveSnapshot(msg: Option[String] = None) extends Command

  def buildInfoItemFromProtobuf(_path: String, pinfo: PInfoItem): InfoItem = {
    val path: Path = Path(_path)
    InfoItem(
      path.last,
      path,
      Option(pinfo.typeName).filter(_.nonEmpty),
      pinfo.names.map(id => buildQlmIDFromProtobuf(id)).toVector,
      pinfo.descriptions.map(d => Description(d.text, Option(d.lang).filter(_.nonEmpty))).toSet,
      metaData = pinfo.metadata
        .map(md => MetaData(md.infoItems.map { case (p, ii) => buildInfoItemFromProtobuf(p, ii) }.toVector)),
      attributes = pinfo.attributes
    )
  }

  def buildQlmIDFromProtobuf(pqlmid: PQlmid): QlmID = {
    QlmID(
      pqlmid.id,
      Option(pqlmid.idType).filter(_.nonEmpty),
      Option(pqlmid.tagType).filter(_.nonEmpty),
      pqlmid.startTime.map(time => new Timestamp(time.time)),
      pqlmid.endTime.map(time => new Timestamp(time.time)),
      pqlmid.attirbutes
    )
  }

  def buildObjectFromProtobuf(path: String, pobj: PObject): Object = {
    Object(
      pobj.ids.map(id => buildQlmIDFromProtobuf(id)).toVector,
      Path(path),
      Option(pobj.typeName).filter(_.nonEmpty),
      pobj.descriptions.map(d => Description(d.text, Option(d.lang).filter(_.nonEmpty))).toSet,
      pobj.attributes

    )
  }

  def buildObjectsFromProtobuf(pobjs: PObjects): Objects = {
    Objects(
      Option(pobjs.version).filter(_.nonEmpty),
      pobjs.attributes
    )
  }

  def buildImmutableOdfFromProtobuf(in: Map[String, PPersistentNode]): ImmutableODF = {
    ImmutableODF(
      in.map { case (k, v) =>
        v.nodeType match {
          case Ii(pinfo) =>
            buildInfoItemFromProtobuf(k, pinfo)
          case Obj(pobject) =>
            buildObjectFromProtobuf(k, pobject)
          case Objs(pobjects) =>
            buildObjectsFromProtobuf(pobjects)
          case other => throw new Exception(s"Invalid nodeType found $other")
        }
      }
    )
  }

  def mergeSubs(a: Map[Path, Seq[EventSub]], b: Map[Path, Seq[EventSub]]): Map[Path, Seq[EventSub]] = {
    merge(a, b) { case (v1, v2) =>
      v2.map(subs => subs ++ v1).getOrElse(v1)
    }
  }

  def asValue(pv: PPersistentValue): Value[Any] = {
    pv.typeName match {
      case "xs:float" if pv.valueType.isProtoDoubleValue =>
        FloatValue(pv.getProtoDoubleValue.toFloat, new Timestamp(pv.timeStamp))
      case "xs:double" if pv.valueType.isProtoDoubleValue =>
        DoubleValue(pv.getProtoDoubleValue, new Timestamp(pv.timeStamp))
      case "xs:short" if pv.valueType.isProtoLongValue =>
        ShortValue(pv.getProtoLongValue.toShort, new Timestamp(pv.timeStamp))
      case "xs:int" if pv.valueType.isProtoLongValue =>
        IntValue(pv.getProtoLongValue.toInt, new Timestamp(pv.timeStamp))
      case "xs:long" if pv.valueType.isProtoLongValue =>
        LongValue(pv.getProtoLongValue, new Timestamp(pv.timeStamp))
      case "xs:boolean" if pv.valueType.isProtoBoolValue =>
        BooleanValue(pv.getProtoBoolValue, new Timestamp(pv.timeStamp))
      case "odf" if pv.valueType.isProtoStringValue =>
        ODFValue(ODFParser.parse(pv.getProtoStringValue).right.get, new Timestamp(pv.timeStamp))
      case str: String if pv.valueType.isProtoStringValue =>
        Value(pv.getProtoStringValue, pv.typeName, new Timestamp(pv.timeStamp))
      case other => throw new Exception(s"Error while deserializing value: $other")
    }
  }
}

