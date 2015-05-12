// Generated by <a href="http://scalaxb.org/">scalaxb</a>.
    
package parsing
package xmlGen
/**
usage:
val obj = scalaxb.fromXML[.Foo](node)
val document = scalaxb.toXML[.Foo](obj, "foo", .defaultScope)
**/
import scala.language.postfixOps

object `package` extends XMLProtocol { }

trait XMLProtocol extends scalaxb.XMLStandardTypes {
  val defaultScope = scalaxb.toScope(None -> "omi.xsd",
    Some("tns0") -> "odf.xsd",
    Some("tns") -> "omi.xsd",
    Some("xs") -> "http://www.w3.org/2001/XMLSchema",
    Some("xsi") -> "http://www.w3.org/2001/XMLSchema-instance")
  implicit lazy val OmiEnvelopeFormat: scalaxb.XMLFormat[OmiEnvelope] = new DefaultOmiEnvelopeFormat {}
  implicit lazy val TargetTypeFormat: scalaxb.XMLFormat[TargetType] = new DefaultTargetTypeFormat {}
  implicit lazy val RequestBaseTypableFormat: scalaxb.XMLFormat[RequestBaseTypable] = new DefaultRequestBaseTypableFormat {}
  implicit lazy val RequestBaseTypeFormat: scalaxb.XMLFormat[RequestBaseType] = new DefaultRequestBaseTypeFormat {}
  implicit lazy val ReadRequestFormat: scalaxb.XMLFormat[ReadRequest] = new DefaultReadRequestFormat {}
  implicit lazy val WriteRequestFormat: scalaxb.XMLFormat[WriteRequest] = new DefaultWriteRequestFormat {}
  implicit lazy val ResponseListTypeFormat: scalaxb.XMLFormat[ResponseListType] = new DefaultResponseListTypeFormat {}
  implicit lazy val TargetTypeTypeFormat: scalaxb.XMLFormat[TargetTypeType] = new DefaultTargetTypeTypeFormat {}
  implicit lazy val RequestResultTypeFormat: scalaxb.XMLFormat[RequestResultType] = new DefaultRequestResultTypeFormat {}
  implicit lazy val ReturnTypeFormat: scalaxb.XMLFormat[ReturnType] = new DefaultReturnTypeFormat {}
  implicit lazy val NodesTypeFormat: scalaxb.XMLFormat[NodesType] = new DefaultNodesTypeFormat {}
  implicit lazy val IdTypeFormat: scalaxb.XMLFormat[IdType] = new DefaultIdTypeFormat {}
  implicit lazy val CancelRequestFormat: scalaxb.XMLFormat[CancelRequest] = new DefaultCancelRequestFormat {}
  implicit lazy val ObjectsTypeFormat: scalaxb.XMLFormat[ObjectsType] = new DefaultObjectsTypeFormat {}
  implicit lazy val ObjectTypeFormat: scalaxb.XMLFormat[ObjectType] = new DefaultObjectTypeFormat {}
  implicit lazy val MetaDataFormat: scalaxb.XMLFormat[MetaData] = new DefaultMetaDataFormat {}
  implicit lazy val InfoItemTypeFormat: scalaxb.XMLFormat[InfoItemType] = new DefaultInfoItemTypeFormat {}
  implicit lazy val DescriptionFormat: scalaxb.XMLFormat[Description] = new DefaultDescriptionFormat {}
  implicit lazy val QlmIDFormat: scalaxb.XMLFormat[QlmID] = new DefaultQlmIDFormat {}
  implicit lazy val ValueTypeFormat: scalaxb.XMLFormat[ValueType] = new DefaultValueTypeFormat {}

  trait DefaultOmiEnvelopeFormat extends scalaxb.ElemNameParser[OmiEnvelope] {
    val targetNamespace: Option[String] = Some("omi.xsd")
    
    def parser(node: scala.xml.Node, stack: List[scalaxb.ElemName]): Parser[OmiEnvelope] =
      phrase((((scalaxb.ElemName(Some("omi.xsd"), "read")) ^^ 
      (x => scalaxb.DataRecord(x.namespace, Some(x.name), scalaxb.fromXML[ReadRequest](x, scalaxb.ElemName(node) :: stack)))) | 
      ((scalaxb.ElemName(Some("omi.xsd"), "write")) ^^ 
      (x => scalaxb.DataRecord(x.namespace, Some(x.name), scalaxb.fromXML[WriteRequest](x, scalaxb.ElemName(node) :: stack)))) | 
      ((scalaxb.ElemName(Some("omi.xsd"), "response")) ^^ 
      (x => scalaxb.DataRecord(x.namespace, Some(x.name), scalaxb.fromXML[ResponseListType](x, scalaxb.ElemName(node) :: stack)))) | 
      ((scalaxb.ElemName(Some("omi.xsd"), "cancel")) ^^ 
      (x => scalaxb.DataRecord(x.namespace, Some(x.name), scalaxb.fromXML[CancelRequest](x, scalaxb.ElemName(node) :: stack))))) ^^
      { case p1 =>
      OmiEnvelope(p1,
        scalaxb.fromXML[String]((node \ "@version"), scalaxb.ElemName(node) :: stack),
        scalaxb.fromXML[Double]((node \ "@ttl"), scalaxb.ElemName(node) :: stack)) })
    
    override def writesAttribute(__obj: OmiEnvelope, __scope: scala.xml.NamespaceBinding): scala.xml.MetaData = {
      var attr: scala.xml.MetaData  = scala.xml.Null
      attr = scala.xml.Attribute(null, "version", __obj.version.toString, attr)
      attr = scala.xml.Attribute(null, "ttl", __obj.ttl.toString, attr)
      attr
    }

    def writesChildNodes(__obj: OmiEnvelope, __scope: scala.xml.NamespaceBinding): Seq[scala.xml.Node] =
      (Some(__obj.omienvelopeoption) map {x => scalaxb.toXML[scalaxb.DataRecord[OmiEnvelopeOption]](x, x.namespace, x.key, __scope, false)} get)

  }

  def buildTargetTypeFormat = new DefaultTargetTypeFormat {}
  trait DefaultTargetTypeFormat extends scalaxb.XMLFormat[TargetType] {
    val targetNamespace: Option[String] = Some("omi.xsd")
    
    def reads(seq: scala.xml.NodeSeq, stack: List[scalaxb.ElemName]): Either[String, TargetType] = seq match {
      case elem: scala.xml.Elem => Right(TargetType.fromString(elem.text, elem.scope))
      case _ => Right(TargetType.fromString(seq.text, scala.xml.TopScope))
    }
    
    def writes(__obj: TargetType, __namespace: Option[String], __elementLabel: Option[String],
        __scope: scala.xml.NamespaceBinding, __typeAttribute: Boolean): scala.xml.NodeSeq =
      scala.xml.Elem(scalaxb.Helper.getPrefix(__namespace, __scope).orNull, 
        __elementLabel getOrElse { sys.error("missing element label.") },
        scala.xml.Null, __scope, scala.xml.Text(__obj.toString))
  }

  trait DefaultRequestBaseTypableFormat extends scalaxb.XMLFormat[RequestBaseTypable] {
    def reads(seq: scala.xml.NodeSeq, stack: List[scalaxb.ElemName]): Either[String, RequestBaseTypable] = seq match {
      case node: scala.xml.Node =>     
        scalaxb.Helper.instanceType(node) match {
          case (Some("omi.xsd"), Some("readRequest")) => Right(scalaxb.fromXML[ReadRequest](node, stack))
          case (Some("omi.xsd"), Some("writeRequest")) => Right(scalaxb.fromXML[WriteRequest](node, stack))
          case _ => Right(scalaxb.fromXML[RequestBaseType](node, stack))
        }
      case _ => Left("reads failed: seq must be scala.xml.Node")  
    }
    
    def writes(__obj: RequestBaseTypable, __namespace: Option[String], __elementLabel: Option[String],
        __scope: scala.xml.NamespaceBinding, __typeAttribute: Boolean): scala.xml.NodeSeq = __obj match {
      case x: ReadRequest => scalaxb.toXML[ReadRequest](x, __namespace, __elementLabel, __scope, true)
      case x: WriteRequest => scalaxb.toXML[WriteRequest](x, __namespace, __elementLabel, __scope, true)
      case x: RequestBaseType => scalaxb.toXML[RequestBaseType](x, __namespace, __elementLabel, __scope, false)
    }
  }

  trait DefaultRequestBaseTypeFormat extends scalaxb.ElemNameParser[RequestBaseType] {
    val targetNamespace: Option[String] = Some("omi.xsd")
    
    override def typeName: Option[String] = Some("requestBaseType")

    def parser(node: scala.xml.Node, stack: List[scalaxb.ElemName]): Parser[RequestBaseType] =
      phrase(opt(scalaxb.ElemName(Some("omi.xsd"), "nodeList")) ~ 
      rep(scalaxb.ElemName(Some("omi.xsd"), "requestId")) ~ 
      opt(any(_ => true)) ^^
      { case p1 ~ p2 ~ p3 =>
      RequestBaseType(p1.headOption map { scalaxb.fromXML[NodesType](_, scalaxb.ElemName(node) :: stack) },
        p2.toSeq map { scalaxb.fromXML[IdType](_, scalaxb.ElemName(node) :: stack) },
        p3.headOption map { scalaxb.fromXML[scalaxb.DataRecord[Any]](_, scalaxb.ElemName(node) :: stack) },
        (node \ "@callback").headOption map { scalaxb.fromXML[java.net.URI](_, scalaxb.ElemName(node) :: stack) },
        (node \ "@msgformat").headOption map { scalaxb.fromXML[String](_, scalaxb.ElemName(node) :: stack) },
        (node \ "@targetType").headOption map { scalaxb.fromXML[TargetType](_, scalaxb.ElemName(node) :: stack) } getOrElse { scalaxb.fromXML[TargetType](scala.xml.Text("node"), scalaxb.ElemName(node) :: stack) }) })
    
    override def writesAttribute(__obj: RequestBaseType, __scope: scala.xml.NamespaceBinding): scala.xml.MetaData = {
      var attr: scala.xml.MetaData  = scala.xml.Null
      __obj.callback foreach { x => attr = scala.xml.Attribute(null, "callback", x.toString, attr) }
      __obj.msgformat foreach { x => attr = scala.xml.Attribute(null, "msgformat", x.toString, attr) }
      if (__obj.targetType.toString != "node") attr = scala.xml.Attribute(null, "targetType", __obj.targetType.toString, attr)
      attr
    }

    def writesChildNodes(__obj: RequestBaseType, __scope: scala.xml.NamespaceBinding): Seq[scala.xml.Node] =
      Seq.concat(__obj.nodeList map { scalaxb.toXML[NodesType](_, Some("omi.xsd"), Some("nodeList"), __scope, false) } getOrElse {Nil},
        __obj.requestId flatMap { scalaxb.toXML[IdType](_, Some("omi.xsd"), Some("requestId"), __scope, false) },
        __obj.msg map { x => scalaxb.toXML[scalaxb.DataRecord[Any]](x, x.namespace, x.key, __scope, true) } getOrElse {Nil})

  }

  trait DefaultReadRequestFormat extends scalaxb.ElemNameParser[ReadRequest] {
    val targetNamespace: Option[String] = Some("omi.xsd")
    
    override def typeName: Option[String] = Some("readRequest")

    def parser(node: scala.xml.Node, stack: List[scalaxb.ElemName]): Parser[ReadRequest] =
      phrase(opt(scalaxb.ElemName(Some("omi.xsd"), "nodeList")) ~ 
      rep(scalaxb.ElemName(Some("omi.xsd"), "requestId")) ~ 
      opt(any(_ => true)) ^^
      { case p1 ~ p2 ~ p3 =>
      ReadRequest(p1.headOption map { scalaxb.fromXML[NodesType](_, scalaxb.ElemName(node) :: stack) },
        p2.toSeq map { scalaxb.fromXML[IdType](_, scalaxb.ElemName(node) :: stack) },
        p3.headOption map { scalaxb.fromXML[scalaxb.DataRecord[Any]](_, scalaxb.ElemName(node) :: stack) },
        (node \ "@callback").headOption map { scalaxb.fromXML[java.net.URI](_, scalaxb.ElemName(node) :: stack) },
        (node \ "@msgformat").headOption map { scalaxb.fromXML[String](_, scalaxb.ElemName(node) :: stack) },
        (node \ "@targetType").headOption map { scalaxb.fromXML[TargetType](_, scalaxb.ElemName(node) :: stack) } getOrElse { scalaxb.fromXML[TargetType](scala.xml.Text("node"), scalaxb.ElemName(node) :: stack) },
        (node \ "@interval").headOption map { scalaxb.fromXML[Double](_, scalaxb.ElemName(node) :: stack) },
        (node \ "@oldest").headOption map { scalaxb.fromXML[Int](_, scalaxb.ElemName(node) :: stack) },
        (node \ "@begin").headOption map { scalaxb.fromXML[javax.xml.datatype.XMLGregorianCalendar](_, scalaxb.ElemName(node) :: stack) },
        (node \ "@end").headOption map { scalaxb.fromXML[javax.xml.datatype.XMLGregorianCalendar](_, scalaxb.ElemName(node) :: stack) },
        (node \ "@newest").headOption map { scalaxb.fromXML[Int](_, scalaxb.ElemName(node) :: stack) }) })
    
    override def writesAttribute(__obj: ReadRequest, __scope: scala.xml.NamespaceBinding): scala.xml.MetaData = {
      var attr: scala.xml.MetaData  = scala.xml.Null
      __obj.callback foreach { x => attr = scala.xml.Attribute(null, "callback", x.toString, attr) }
      __obj.msgformat foreach { x => attr = scala.xml.Attribute(null, "msgformat", x.toString, attr) }
      if (__obj.targetType.toString != "node") attr = scala.xml.Attribute(null, "targetType", __obj.targetType.toString, attr)
      __obj.interval foreach { x => attr = scala.xml.Attribute(null, "interval", x.toString, attr) }
      __obj.oldest foreach { x => attr = scala.xml.Attribute(null, "oldest", x.toString, attr) }
      __obj.begin foreach { x => attr = scala.xml.Attribute(null, "begin", x.toString, attr) }
      __obj.end foreach { x => attr = scala.xml.Attribute(null, "end", x.toString, attr) }
      __obj.newest foreach { x => attr = scala.xml.Attribute(null, "newest", x.toString, attr) }
      attr
    }

    def writesChildNodes(__obj: ReadRequest, __scope: scala.xml.NamespaceBinding): Seq[scala.xml.Node] =
      Seq.concat(__obj.nodeList map { scalaxb.toXML[NodesType](_, Some("omi.xsd"), Some("nodeList"), __scope, false) } getOrElse {Nil},
        __obj.requestId flatMap { scalaxb.toXML[IdType](_, Some("omi.xsd"), Some("requestId"), __scope, false) },
        __obj.msg map { x => scalaxb.toXML[scalaxb.DataRecord[Any]](x, x.namespace, x.key, __scope, true) } getOrElse {Nil})

  }

  trait DefaultWriteRequestFormat extends scalaxb.ElemNameParser[WriteRequest] {
    val targetNamespace: Option[String] = Some("omi.xsd")
    
    override def typeName: Option[String] = Some("writeRequest")

    def parser(node: scala.xml.Node, stack: List[scalaxb.ElemName]): Parser[WriteRequest] =
      phrase(opt(scalaxb.ElemName(Some("omi.xsd"), "nodeList")) ~ 
      rep(scalaxb.ElemName(Some("omi.xsd"), "requestId")) ~ 
      opt(any(_ => true)) ^^
      { case p1 ~ p2 ~ p3 =>
      WriteRequest(p1.headOption map { scalaxb.fromXML[NodesType](_, scalaxb.ElemName(node) :: stack) },
        p2.toSeq map { scalaxb.fromXML[IdType](_, scalaxb.ElemName(node) :: stack) },
        p3.headOption map { scalaxb.fromXML[scalaxb.DataRecord[Any]](_, scalaxb.ElemName(node) :: stack) },
        (node \ "@callback").headOption map { scalaxb.fromXML[java.net.URI](_, scalaxb.ElemName(node) :: stack) },
        (node \ "@msgformat").headOption map { scalaxb.fromXML[String](_, scalaxb.ElemName(node) :: stack) },
        (node \ "@targetType").headOption map { scalaxb.fromXML[TargetType](_, scalaxb.ElemName(node) :: stack) } getOrElse { scalaxb.fromXML[TargetType](scala.xml.Text("node"), scalaxb.ElemName(node) :: stack) }) })
    
    override def writesAttribute(__obj: WriteRequest, __scope: scala.xml.NamespaceBinding): scala.xml.MetaData = {
      var attr: scala.xml.MetaData  = scala.xml.Null
      __obj.callback foreach { x => attr = scala.xml.Attribute(null, "callback", x.toString, attr) }
      __obj.msgformat foreach { x => attr = scala.xml.Attribute(null, "msgformat", x.toString, attr) }
      if (__obj.targetType.toString != "node") attr = scala.xml.Attribute(null, "targetType", __obj.targetType.toString, attr)
      attr
    }

    def writesChildNodes(__obj: WriteRequest, __scope: scala.xml.NamespaceBinding): Seq[scala.xml.Node] =
      Seq.concat(__obj.nodeList map { scalaxb.toXML[NodesType](_, Some("omi.xsd"), Some("nodeList"), __scope, false) } getOrElse {Nil},
        __obj.requestId flatMap { scalaxb.toXML[IdType](_, Some("omi.xsd"), Some("requestId"), __scope, false) },
        __obj.msg map { x => scalaxb.toXML[scalaxb.DataRecord[Any]](x, x.namespace, x.key, __scope, true) } getOrElse {Nil})

  }

  trait DefaultResponseListTypeFormat extends scalaxb.ElemNameParser[ResponseListType] {
    val targetNamespace: Option[String] = Some("omi.xsd")
    
    override def typeName: Option[String] = Some("responseListType")

    def parser(node: scala.xml.Node, stack: List[scalaxb.ElemName]): Parser[ResponseListType] =
      phrase(rep(scalaxb.ElemName(Some("omi.xsd"), "result")) ^^
      { case p1 =>
      ResponseListType(p1.toSeq map { scalaxb.fromXML[RequestResultType](_, scalaxb.ElemName(node) :: stack) }: _*) })
    
    def writesChildNodes(__obj: ResponseListType, __scope: scala.xml.NamespaceBinding): Seq[scala.xml.Node] =
      (__obj.result flatMap { scalaxb.toXML[RequestResultType](_, Some("omi.xsd"), Some("result"), __scope, false) })

  }

  def buildTargetTypeTypeFormat = new DefaultTargetTypeTypeFormat {}
  trait DefaultTargetTypeTypeFormat extends scalaxb.XMLFormat[TargetTypeType] {
    val targetNamespace: Option[String] = Some("omi.xsd")
    
    def reads(seq: scala.xml.NodeSeq, stack: List[scalaxb.ElemName]): Either[String, TargetTypeType] = seq match {
      case elem: scala.xml.Elem => Right(TargetTypeType.fromString(elem.text, elem.scope))
      case _ => Right(TargetTypeType.fromString(seq.text, scala.xml.TopScope))
    }
    
    def writes(__obj: TargetTypeType, __namespace: Option[String], __elementLabel: Option[String],
        __scope: scala.xml.NamespaceBinding, __typeAttribute: Boolean): scala.xml.NodeSeq =
      scala.xml.Elem(scalaxb.Helper.getPrefix(__namespace, __scope).orNull, 
        __elementLabel getOrElse { sys.error("missing element label.") },
        scala.xml.Null, __scope, scala.xml.Text(__obj.toString))
  }

  trait DefaultRequestResultTypeFormat extends scalaxb.ElemNameParser[RequestResultType] {
    val targetNamespace: Option[String] = Some("omi.xsd")
    
    override def typeName: Option[String] = Some("requestResultType")

    def parser(node: scala.xml.Node, stack: List[scalaxb.ElemName]): Parser[RequestResultType] =
      phrase((scalaxb.ElemName(Some("omi.xsd"), "return")) ~ 
      opt(scalaxb.ElemName(Some("omi.xsd"), "requestId")) ~ 
      opt(any(_ => true)) ~ 
      opt(scalaxb.ElemName(Some("omi.xsd"), "nodeList")) ~ 
      opt(scalaxb.ElemName(Some("omi.xsd"), "omiEnvelope")) ^^
      { case p1 ~ p2 ~ p3 ~ p4 ~ p5 =>
      RequestResultType(scalaxb.fromXML[ReturnType](p1, scalaxb.ElemName(node) :: stack),
        p2.headOption map { scalaxb.fromXML[IdType](_, scalaxb.ElemName(node) :: stack) },
        p3.headOption map { scalaxb.fromXML[scalaxb.DataRecord[Any]](_, scalaxb.ElemName(node) :: stack) },
        p4.headOption map { scalaxb.fromXML[NodesType](_, scalaxb.ElemName(node) :: stack) },
        p5.headOption map { scalaxb.fromXML[OmiEnvelope](_, scalaxb.ElemName(node) :: stack) },
        (node \ "@msgformat").headOption map { scalaxb.fromXML[String](_, scalaxb.ElemName(node) :: stack) },
        (node \ "@targetType").headOption map { scalaxb.fromXML[TargetTypeType](_, scalaxb.ElemName(node) :: stack) } getOrElse { scalaxb.fromXML[TargetTypeType](scala.xml.Text("node"), scalaxb.ElemName(node) :: stack) }) })
    
    override def writesAttribute(__obj: RequestResultType, __scope: scala.xml.NamespaceBinding): scala.xml.MetaData = {
      var attr: scala.xml.MetaData  = scala.xml.Null
      __obj.msgformat foreach { x => attr = scala.xml.Attribute(null, "msgformat", x.toString, attr) }
      if (__obj.targetType.toString != "node") attr = scala.xml.Attribute(null, "targetType", __obj.targetType.toString, attr)
      attr
    }

    def writesChildNodes(__obj: RequestResultType, __scope: scala.xml.NamespaceBinding): Seq[scala.xml.Node] =
      Seq.concat(scalaxb.toXML[ReturnType](__obj.returnValue, Some("omi.xsd"), Some("return"), __scope, false),
        __obj.requestId map { scalaxb.toXML[IdType](_, Some("omi.xsd"), Some("requestId"), __scope, false) } getOrElse {Nil},
        __obj.msg map { x => scalaxb.toXML[scalaxb.DataRecord[Any]](x, x.namespace, x.key, __scope, true) } getOrElse {Nil},
        __obj.nodeList map { scalaxb.toXML[NodesType](_, Some("omi.xsd"), Some("nodeList"), __scope, false) } getOrElse {Nil},
        __obj.omiEnvelope map { scalaxb.toXML[OmiEnvelope](_, Some("omi.xsd"), Some("omiEnvelope"), __scope, false) } getOrElse {Nil})

  }

  trait DefaultReturnTypeFormat extends scalaxb.XMLFormat[ReturnType] with scalaxb.CanWriteChildNodes[ReturnType] {
    val targetNamespace: Option[String] = Some("omi.xsd")
    import scalaxb.ElemName._
    
    def reads(seq: scala.xml.NodeSeq, stack: List[scalaxb.ElemName]): Either[String, ReturnType] = seq match {
      case node: scala.xml.Node => Right(ReturnType(scalaxb.fromXML[String](node, scalaxb.ElemName(node) :: stack),
        scalaxb.fromXML[String]((node \ "@returnCode"), scalaxb.ElemName(node) :: stack),
        (node \ "@description").headOption map { scalaxb.fromXML[String](_, scalaxb.ElemName(node) :: stack) },
        scala.collection.immutable.ListMap((node match {
          case elem: scala.xml.Elem =>
            elem.attributes.toList flatMap {
              case scala.xml.UnprefixedAttribute(key, value, _) if key == "returnCode" => Nil
              case scala.xml.UnprefixedAttribute(key, value, _) if key == "description" => Nil
              case scala.xml.UnprefixedAttribute(key, value, _) =>
                List(("@" + key, scalaxb.DataRecord(None, Some(key), value.text)))
              case scala.xml.PrefixedAttribute(pre, key, value, _) =>
                val ns = elem.scope.getURI(pre)
                List(("@{" + ns + "}" + key, scalaxb.DataRecord(Option[String](ns), Some(key), value.text)))
              case _ => Nil
            }
          case _ => Nil
        }): _*)))
      case _ => Left("reads failed: seq must be scala.xml.Node")
    }
    
    override def writesAttribute(__obj: ReturnType, __scope: scala.xml.NamespaceBinding): scala.xml.MetaData = {
      var attr: scala.xml.MetaData  = scala.xml.Null
      attr = scala.xml.Attribute(null, "returnCode", __obj.returnCode.toString, attr)
      __obj.description foreach { x => attr = scala.xml.Attribute(null, "description", x.toString, attr) }
      __obj.attributes.toList map {
        case (key, x) => attr = scala.xml.Attribute((x.namespace map { __scope.getPrefix(_) }).orNull, x.key.orNull, x.value.toString, attr) }
      attr
    }

    def writesChildNodes(__obj: ReturnType, __scope: scala.xml.NamespaceBinding): Seq[scala.xml.Node] =
      Seq(scala.xml.Text(__obj.value.toString))


  }

  trait DefaultNodesTypeFormat extends scalaxb.ElemNameParser[NodesType] {
    val targetNamespace: Option[String] = Some("omi.xsd")
    
    override def typeName: Option[String] = Some("nodesType")

    def parser(node: scala.xml.Node, stack: List[scalaxb.ElemName]): Parser[NodesType] =
      phrase(rep(scalaxb.ElemName(Some("omi.xsd"), "node")) ^^
      { case p1 =>
      NodesType(p1.toSeq map { scalaxb.fromXML[java.net.URI](_, scalaxb.ElemName(node) :: stack) },
        (node \ "@type").headOption map { scalaxb.fromXML[String](_, scalaxb.ElemName(node) :: stack) }) })
    
    override def writesAttribute(__obj: NodesType, __scope: scala.xml.NamespaceBinding): scala.xml.MetaData = {
      var attr: scala.xml.MetaData  = scala.xml.Null
      __obj.typeValue foreach { x => attr = scala.xml.Attribute(null, "type", x.toString, attr) }
      attr
    }

    def writesChildNodes(__obj: NodesType, __scope: scala.xml.NamespaceBinding): Seq[scala.xml.Node] =
      (__obj.node flatMap { scalaxb.toXML[java.net.URI](_, Some("omi.xsd"), Some("node"), __scope, false) })

  }

  trait DefaultIdTypeFormat extends scalaxb.XMLFormat[IdType] with scalaxb.CanWriteChildNodes[IdType] {
    val targetNamespace: Option[String] = Some("omi.xsd")
    import scalaxb.ElemName._
    
    def reads(seq: scala.xml.NodeSeq, stack: List[scalaxb.ElemName]): Either[String, IdType] = seq match {
      case node: scala.xml.Node => Right(IdType(scalaxb.fromXML[String](node, scalaxb.ElemName(node) :: stack),
        (node \ "@format").headOption map { scalaxb.fromXML[String](_, scalaxb.ElemName(node) :: stack) }))
      case _ => Left("reads failed: seq must be scala.xml.Node")
    }
    
    override def writesAttribute(__obj: IdType, __scope: scala.xml.NamespaceBinding): scala.xml.MetaData = {
      var attr: scala.xml.MetaData  = scala.xml.Null
      __obj.format foreach { x => attr = scala.xml.Attribute(null, "format", x.toString, attr) }
      attr
    }

    def writesChildNodes(__obj: IdType, __scope: scala.xml.NamespaceBinding): Seq[scala.xml.Node] =
      Seq(scala.xml.Text(__obj.value.toString))


  }

  trait DefaultCancelRequestFormat extends scalaxb.ElemNameParser[CancelRequest] {
    val targetNamespace: Option[String] = Some("omi.xsd")
    
    override def typeName: Option[String] = Some("cancelRequest")

    def parser(node: scala.xml.Node, stack: List[scalaxb.ElemName]): Parser[CancelRequest] =
      phrase(opt(scalaxb.ElemName(Some("omi.xsd"), "nodeList")) ~ 
      rep(scalaxb.ElemName(Some("omi.xsd"), "requestId")) ^^
      { case p1 ~ p2 =>
      CancelRequest(p1.headOption map { scalaxb.fromXML[NodesType](_, scalaxb.ElemName(node) :: stack) },
        p2.toSeq map { scalaxb.fromXML[IdType](_, scalaxb.ElemName(node) :: stack) }) })
    
    def writesChildNodes(__obj: CancelRequest, __scope: scala.xml.NamespaceBinding): Seq[scala.xml.Node] =
      Seq.concat(__obj.nodeList map { scalaxb.toXML[NodesType](_, Some("omi.xsd"), Some("nodeList"), __scope, false) } getOrElse {Nil},
        __obj.requestId flatMap { scalaxb.toXML[IdType](_, Some("omi.xsd"), Some("requestId"), __scope, false) })

  }

  trait DefaultObjectsTypeFormat extends scalaxb.ElemNameParser[ObjectsType] {
    val targetNamespace: Option[String] = Some("odf.xsd")
    
    override def typeName: Option[String] = Some("ObjectsType")

    def parser(node: scala.xml.Node, stack: List[scalaxb.ElemName]): Parser[ObjectsType] =
      phrase(rep(scalaxb.ElemName(Some("odf.xsd"), "Object")) ^^
      { case p1 =>
      ObjectsType(p1.toSeq map { scalaxb.fromXML[ObjectType](_, scalaxb.ElemName(node) :: stack) },
        (node \ "@version").headOption map { scalaxb.fromXML[String](_, scalaxb.ElemName(node) :: stack) }) })
    
    override def writesAttribute(__obj: ObjectsType, __scope: scala.xml.NamespaceBinding): scala.xml.MetaData = {
      var attr: scala.xml.MetaData  = scala.xml.Null
      __obj.version foreach { x => attr = scala.xml.Attribute(null, "version", x.toString, attr) }
      attr
    }

    def writesChildNodes(__obj: ObjectsType, __scope: scala.xml.NamespaceBinding): Seq[scala.xml.Node] =
      (__obj.Object flatMap { scalaxb.toXML[ObjectType](_, Some("odf.xsd"), Some("Object"), __scope, false) })

  }

  trait DefaultObjectTypeFormat extends scalaxb.ElemNameParser[ObjectType] {
    val targetNamespace: Option[String] = Some("odf.xsd")
    
    override def typeName: Option[String] = Some("ObjectType")

    def parser(node: scala.xml.Node, stack: List[scalaxb.ElemName]): Parser[ObjectType] =
      phrase(rep(scalaxb.ElemName(Some("odf.xsd"), "id")) ~ 
      opt(scalaxb.ElemName(Some("odf.xsd"), "description")) ~ 
      rep(scalaxb.ElemName(Some("odf.xsd"), "InfoItem")) ~ 
      rep(scalaxb.ElemName(Some("odf.xsd"), "Object")) ^^
      { case p1 ~ p2 ~ p3 ~ p4 =>
      ObjectType(p1.toSeq map { scalaxb.fromXML[QlmID](_, scalaxb.ElemName(node) :: stack) },
        p2.headOption map { scalaxb.fromXML[Description](_, scalaxb.ElemName(node) :: stack) },
        p3.toSeq map { scalaxb.fromXML[InfoItemType](_, scalaxb.ElemName(node) :: stack) },
        p4.toSeq map { scalaxb.fromXML[ObjectType](_, scalaxb.ElemName(node) :: stack) },
        (node \ "@type").headOption map { scalaxb.fromXML[String](_, scalaxb.ElemName(node) :: stack) },
        scala.collection.immutable.ListMap((node match {
          case elem: scala.xml.Elem =>
            elem.attributes.toList flatMap {
              case scala.xml.UnprefixedAttribute(key, value, _) if key == "type" => Nil
              case scala.xml.UnprefixedAttribute(key, value, _) =>
                List(("@" + key, scalaxb.DataRecord(None, Some(key), value.text)))
              case scala.xml.PrefixedAttribute(pre, key, value, _) =>
                val ns = elem.scope.getURI(pre)
                List(("@{" + ns + "}" + key, scalaxb.DataRecord(Option[String](ns), Some(key), value.text)))
              case _ => Nil
            }
          case _ => Nil
        }): _*)) })
    
    override def writesAttribute(__obj: ObjectType, __scope: scala.xml.NamespaceBinding): scala.xml.MetaData = {
      var attr: scala.xml.MetaData  = scala.xml.Null
      __obj.typeValue foreach { x => attr = scala.xml.Attribute(null, "type", x.toString, attr) }
      __obj.attributes.toList map {
        case (key, x) => attr = scala.xml.Attribute((x.namespace map { __scope.getPrefix(_) }).orNull, x.key.orNull, x.value.toString, attr) }
      attr
    }

    def writesChildNodes(__obj: ObjectType, __scope: scala.xml.NamespaceBinding): Seq[scala.xml.Node] =
      Seq.concat(__obj.id flatMap { scalaxb.toXML[QlmID](_, Some("odf.xsd"), Some("id"), __scope, false) },
        __obj.description map { scalaxb.toXML[Description](_, Some("odf.xsd"), Some("description"), __scope, false) } getOrElse {Nil},
        __obj.InfoItem flatMap { scalaxb.toXML[InfoItemType](_, Some("odf.xsd"), Some("InfoItem"), __scope, false) },
        __obj.Object flatMap { scalaxb.toXML[ObjectType](_, Some("odf.xsd"), Some("Object"), __scope, false) })

  }

  trait DefaultMetaDataFormat extends scalaxb.ElemNameParser[MetaData] {
    val targetNamespace: Option[String] = Some("odf.xsd")
    
    def parser(node: scala.xml.Node, stack: List[scalaxb.ElemName]): Parser[MetaData] =
      phrase(rep(scalaxb.ElemName(Some("odf.xsd"), "InfoItem")) ^^
      { case p1 =>
      MetaData(p1.toSeq map { scalaxb.fromXML[InfoItemType](_, scalaxb.ElemName(node) :: stack) }: _*) })
    
    def writesChildNodes(__obj: MetaData, __scope: scala.xml.NamespaceBinding): Seq[scala.xml.Node] =
      (__obj.InfoItem flatMap { scalaxb.toXML[InfoItemType](_, Some("odf.xsd"), Some("InfoItem"), __scope, false) })

  }

  trait DefaultInfoItemTypeFormat extends scalaxb.ElemNameParser[InfoItemType] {
    val targetNamespace: Option[String] = Some("odf.xsd")
    
    override def typeName: Option[String] = Some("InfoItemType")

    def parser(node: scala.xml.Node, stack: List[scalaxb.ElemName]): Parser[InfoItemType] =
      phrase(rep(scalaxb.ElemName(Some("odf.xsd"), "name")) ~ 
      opt(scalaxb.ElemName(Some("odf.xsd"), "description")) ~ 
      opt(scalaxb.ElemName(Some("odf.xsd"), "MetaData")) ~ 
      rep(scalaxb.ElemName(Some("odf.xsd"), "value")) ^^
      { case p1 ~ p2 ~ p3 ~ p4 =>
      InfoItemType(p1.toSeq map { scalaxb.fromXML[QlmID](_, scalaxb.ElemName(node) :: stack) },
        p2.headOption map { scalaxb.fromXML[Description](_, scalaxb.ElemName(node) :: stack) },
        p3.headOption map { scalaxb.fromXML[MetaData](_, scalaxb.ElemName(node) :: stack) },
        p4.toSeq map { scalaxb.fromXML[ValueType](_, scalaxb.ElemName(node) :: stack) },
        scalaxb.fromXML[String]((node \ "@name"), scalaxb.ElemName(node) :: stack),
        scala.collection.immutable.ListMap((node match {
          case elem: scala.xml.Elem =>
            elem.attributes.toList flatMap {
              case scala.xml.UnprefixedAttribute(key, value, _) if key == "name" => Nil
              case scala.xml.UnprefixedAttribute(key, value, _) =>
                List(("@" + key, scalaxb.DataRecord(None, Some(key), value.text)))
              case scala.xml.PrefixedAttribute(pre, key, value, _) =>
                val ns = elem.scope.getURI(pre)
                List(("@{" + ns + "}" + key, scalaxb.DataRecord(Option[String](ns), Some(key), value.text)))
              case _ => Nil
            }
          case _ => Nil
        }): _*)) })
    
    override def writesAttribute(__obj: InfoItemType, __scope: scala.xml.NamespaceBinding): scala.xml.MetaData = {
      var attr: scala.xml.MetaData  = scala.xml.Null
      attr = scala.xml.Attribute(null, "name", __obj.name.toString, attr)
      __obj.attributes.toList map {
        case (key, x) => attr = scala.xml.Attribute((x.namespace map { __scope.getPrefix(_) }).orNull, x.key.orNull, x.value.toString, attr) }
      attr
    }

    def writesChildNodes(__obj: InfoItemType, __scope: scala.xml.NamespaceBinding): Seq[scala.xml.Node] =
      Seq.concat(__obj.otherName flatMap { scalaxb.toXML[QlmID](_, Some("odf.xsd"), Some("name"), __scope, false) },
        __obj.description map { scalaxb.toXML[Description](_, Some("odf.xsd"), Some("description"), __scope, false) } getOrElse {Nil},
        __obj.MetaData map { scalaxb.toXML[MetaData](_, Some("odf.xsd"), Some("MetaData"), __scope, false) } getOrElse {Nil},
        __obj.value flatMap { scalaxb.toXML[ValueType](_, Some("odf.xsd"), Some("value"), __scope, false) })

  }

  trait DefaultDescriptionFormat extends scalaxb.XMLFormat[Description] with scalaxb.CanWriteChildNodes[Description] {
    val targetNamespace: Option[String] = Some("odf.xsd")
    import scalaxb.ElemName._
    
    def reads(seq: scala.xml.NodeSeq, stack: List[scalaxb.ElemName]): Either[String, Description] = seq match {
      case node: scala.xml.Node => Right(Description(scalaxb.fromXML[String](node, scalaxb.ElemName(node) :: stack),
        (node \ "@lang").headOption map { scalaxb.fromXML[String](_, scalaxb.ElemName(node) :: stack) },
        scala.collection.immutable.ListMap((node match {
          case elem: scala.xml.Elem =>
            elem.attributes.toList flatMap {
              case scala.xml.UnprefixedAttribute(key, value, _) if key == "lang" => Nil
              case scala.xml.UnprefixedAttribute(key, value, _) =>
                List(("@" + key, scalaxb.DataRecord(None, Some(key), value.text)))
              case scala.xml.PrefixedAttribute(pre, key, value, _) =>
                val ns = elem.scope.getURI(pre)
                List(("@{" + ns + "}" + key, scalaxb.DataRecord(Option[String](ns), Some(key), value.text)))
              case _ => Nil
            }
          case _ => Nil
        }): _*)))
      case _ => Left("reads failed: seq must be scala.xml.Node")
    }
    
    override def writesAttribute(__obj: Description, __scope: scala.xml.NamespaceBinding): scala.xml.MetaData = {
      var attr: scala.xml.MetaData  = scala.xml.Null
      __obj.lang foreach { x => attr = scala.xml.Attribute(null, "lang", x.toString, attr) }
      __obj.attributes.toList map {
        case (key, x) => attr = scala.xml.Attribute((x.namespace map { __scope.getPrefix(_) }).orNull, x.key.orNull, x.value.toString, attr) }
      attr
    }

    def writesChildNodes(__obj: Description, __scope: scala.xml.NamespaceBinding): Seq[scala.xml.Node] =
      Seq(scala.xml.Text(__obj.value.toString))


  }

  trait DefaultQlmIDFormat extends scalaxb.XMLFormat[QlmID] with scalaxb.CanWriteChildNodes[QlmID] {
    val targetNamespace: Option[String] = Some("odf.xsd")
    import scalaxb.ElemName._
    
    def reads(seq: scala.xml.NodeSeq, stack: List[scalaxb.ElemName]): Either[String, QlmID] = seq match {
      case node: scala.xml.Node => Right(QlmID(scalaxb.fromXML[String](node, scalaxb.ElemName(node) :: stack),
        (node \ "@idType").headOption map { scalaxb.fromXML[String](_, scalaxb.ElemName(node) :: stack) },
        (node \ "@tagType").headOption map { scalaxb.fromXML[String](_, scalaxb.ElemName(node) :: stack) },
        (node \ "@startDate").headOption map { scalaxb.fromXML[javax.xml.datatype.XMLGregorianCalendar](_, scalaxb.ElemName(node) :: stack) },
        (node \ "@endDate").headOption map { scalaxb.fromXML[javax.xml.datatype.XMLGregorianCalendar](_, scalaxb.ElemName(node) :: stack) },
        scala.collection.immutable.ListMap((node match {
          case elem: scala.xml.Elem =>
            elem.attributes.toList flatMap {
              case scala.xml.UnprefixedAttribute(key, value, _) if key == "idType" => Nil
              case scala.xml.UnprefixedAttribute(key, value, _) if key == "tagType" => Nil
              case scala.xml.UnprefixedAttribute(key, value, _) if key == "startDate" => Nil
              case scala.xml.UnprefixedAttribute(key, value, _) if key == "endDate" => Nil
              case scala.xml.UnprefixedAttribute(key, value, _) =>
                List(("@" + key, scalaxb.DataRecord(None, Some(key), value.text)))
              case scala.xml.PrefixedAttribute(pre, key, value, _) =>
                val ns = elem.scope.getURI(pre)
                List(("@{" + ns + "}" + key, scalaxb.DataRecord(Option[String](ns), Some(key), value.text)))
              case _ => Nil
            }
          case _ => Nil
        }): _*)))
      case _ => Left("reads failed: seq must be scala.xml.Node")
    }
    
    override def writesAttribute(__obj: QlmID, __scope: scala.xml.NamespaceBinding): scala.xml.MetaData = {
      var attr: scala.xml.MetaData  = scala.xml.Null
      __obj.idType foreach { x => attr = scala.xml.Attribute(null, "idType", x.toString, attr) }
      __obj.tagType foreach { x => attr = scala.xml.Attribute(null, "tagType", x.toString, attr) }
      __obj.startDate foreach { x => attr = scala.xml.Attribute(null, "startDate", x.toString, attr) }
      __obj.endDate foreach { x => attr = scala.xml.Attribute(null, "endDate", x.toString, attr) }
      __obj.attributes.toList map {
        case (key, x) => attr = scala.xml.Attribute((x.namespace map { __scope.getPrefix(_) }).orNull, x.key.orNull, x.value.toString, attr) }
      attr
    }

    def writesChildNodes(__obj: QlmID, __scope: scala.xml.NamespaceBinding): Seq[scala.xml.Node] =
      Seq(scala.xml.Text(__obj.value.toString))


  }

  trait DefaultValueTypeFormat extends scalaxb.XMLFormat[ValueType] with scalaxb.CanWriteChildNodes[ValueType] {
    val targetNamespace: Option[String] = Some("odf.xsd")
    import scalaxb.ElemName._
    
    def reads(seq: scala.xml.NodeSeq, stack: List[scalaxb.ElemName]): Either[String, ValueType] = seq match {
      case node: scala.xml.Node => Right(ValueType(scalaxb.fromXML[String](node, scalaxb.ElemName(node) :: stack),
        (node \ "@type").headOption map { scalaxb.fromXML[String](_, scalaxb.ElemName(node) :: stack) } getOrElse { scalaxb.fromXML[String](scala.xml.Text("xs:string"), scalaxb.ElemName(node) :: stack) },
        (node \ "@dateTime").headOption map { scalaxb.fromXML[javax.xml.datatype.XMLGregorianCalendar](_, scalaxb.ElemName(node) :: stack) },
        (node \ "@unixTime").headOption map { scalaxb.fromXML[Long](_, scalaxb.ElemName(node) :: stack) },
        scala.collection.immutable.ListMap((node match {
          case elem: scala.xml.Elem =>
            elem.attributes.toList flatMap {
              case scala.xml.UnprefixedAttribute(key, value, _) if key == "type" => Nil
              case scala.xml.UnprefixedAttribute(key, value, _) if key == "dateTime" => Nil
              case scala.xml.UnprefixedAttribute(key, value, _) if key == "unixTime" => Nil
              case scala.xml.UnprefixedAttribute(key, value, _) =>
                List(("@" + key, scalaxb.DataRecord(None, Some(key), value.text)))
              case scala.xml.PrefixedAttribute(pre, key, value, _) =>
                val ns = elem.scope.getURI(pre)
                List(("@{" + ns + "}" + key, scalaxb.DataRecord(Option[String](ns), Some(key), value.text)))
              case _ => Nil
            }
          case _ => Nil
        }): _*)))
      case _ => Left("reads failed: seq must be scala.xml.Node")
    }
    
    override def writesAttribute(__obj: ValueType, __scope: scala.xml.NamespaceBinding): scala.xml.MetaData = {
      var attr: scala.xml.MetaData  = scala.xml.Null
      if (__obj.typeValue.toString != "xs:string") attr = scala.xml.Attribute(null, "type", __obj.typeValue.toString, attr)
      __obj.dateTime foreach { x => attr = scala.xml.Attribute(null, "dateTime", x.toString, attr) }
      __obj.unixTime foreach { x => attr = scala.xml.Attribute(null, "unixTime", x.toString, attr) }
      __obj.attributes.toList map {
        case (key, x) => attr = scala.xml.Attribute((x.namespace map { __scope.getPrefix(_) }).orNull, x.key.orNull, x.value.toString, attr) }
      attr
    }

    def writesChildNodes(__obj: ValueType, __scope: scala.xml.NamespaceBinding): Seq[scala.xml.Node] =
      Seq(scala.xml.Text(__obj.value.toString))


  }


}

