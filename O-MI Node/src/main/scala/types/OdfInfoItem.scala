/**
  Copyright (c) 2015 Aalto University.

  Licensed under the 4-clause BSD (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

  https://github.com/AaltoAsia/O-MI/blob/master/LICENSE.txt

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
**/
package types
package OdfTypes

import parsing.xmlGen._
import parsing.xmlGen.xmlTypes._
import xml.XML
import java.sql.Timestamp
import java.lang.{Iterable => JavaIterable}
import scala.collection.JavaConversions.{asJavaIterable, iterableAsScalaIterable, seqAsJavaList}
import http.Boot.settings

class  OdfInfoItemImpl(
  path:                 Path,
  values:               JavaIterable[OdfValue] = Iterable(),
  description:          Option[OdfDescription] = None,
  metaData:             Option[OdfMetaData] = None
){
  def apply( data: ( Path, OdfValue ) ) : OdfInfoItem = {
    val (path, odfValue) = data
    OdfInfoItem(path, Iterable( odfValue))
  }
  def apply(path: Path, timestamp: Timestamp, value: String, valueType: String = "") : OdfInfoItem = 
    OdfInfoItem(path, Iterable( OdfValue(value, valueType, Some(timestamp))))

  def combine(another: OdfInfoItem) : OdfInfoItem ={
    require(path == another.path, "Should have same paths")
    OdfInfoItem(
      path,
      (values ++ another.values).toSeq,
      (description, another.description) match{
        case (Some(a), Some(b)) => Some(a)
        case (None, Some(b)) => Some(b)
        case (Some(a), None) => Some(a)
        case (None, None) => None
      },
      (metaData, another.metaData) match{
        case (Some(a), Some(b)) => Some(a)
        case (None, Some(b)) => Some(b)
        case (Some(a), None) => Some(a)
        case (None, None) => None
      }
    )
  }

  def update(another: OdfInfoItem) : (Path, OdfInfoItem) ={
    require(path == another.path, "Should have same paths")
    (
      path,
      OdfInfoItem(
      path,
      ( values ++ another.values ).toSeq.sortWith{ 
        (left,right) => 
        left.timestamp.forall{ l => right.timestamp.forall{ r => l.before( r ) } }
        }.take(1),
      ( description, another.description ) match{
        case (Some(a), Some(b)) => Some(b)
        case (None, Some(b)) => Some(b)
        case (Some(a), None) => Some(a)
        case (None, None) => None
      },
      (metaData, another.metaData) match{
        case (Some(a), Some(b)) => Some(b)
        case (None, Some(b)) => Some(b)
        case (Some(a), None) => Some(a)
        case (None, None) => None
      }
    )
    )
  
  }

  implicit def asInfoItemType: InfoItemType = {
    InfoItemType(
      description = description.map( des => des.asDescription ),
      MetaData = metaData.map{ odfMetaData => odfMetaData.asMetaData},
      name = path.lastOption.getOrElse(throw new IllegalArgumentException(s"OdfObject should have longer than one segment path: ${path}")),
      value = values.map{ 
        value : OdfValue =>
        value.asValueType
      }.toSeq,
      attributes = Map.empty
    )
  }

}

case class OdfMetaData(
  data:                 String
) {
  implicit def asMetaData : MetaData = {
    scalaxb.fromXML[MetaData]( XML.loadString( data ) )
  }
}

case class OdfValue(
  value:                String,
  typeValue:            String,
  timestamp:            Option[Timestamp] = None
) {
  implicit def asValueType : ValueType = {
    ValueType(
      value,
      typeValue,
      unixTime = timestamp.map( _.getTime/1000),
      attributes = Map.empty
    )
  }
}
