package http

import parsing._
import parsing.Types._
import parsing.Types.Path._

import org.specs2.mutable.Specification
import org.specs2.matcher.XmlMatchers
import spray.testkit.Specs2RouteTest
import spray.http._
import spray.http.HttpMethods._
import spray.http.StatusCodes._
import StatusCodes._
import MediaTypes._
import StatusCodes._
import xml._


class OmiServiceSpec extends Specification
                        with XmlMatchers
                        with Specs2RouteTest
                        with OmiService {
    def actorRefFactory = system
    lazy val log = akka.event.Logging.getLogger(actorRefFactory, this)

    Starter.init()
      
    "OmiService (Data discovery)" should {
      
      "respond with hello message for GET request to the root path" in {
        Get() ~> myRoute ~> check{
          mediaType === `text/html`
          responseAs[String] must contain ("Say hello to <i>O-MI Node service")
        }
      }
      
      "respond succesfully to GET to /Objects" in {
        Get("/Objects") ~> myRoute ~> check {
          mediaType === `text/xml`
          status === OK
          responseAs[NodeSeq].head.label === "Objects"
        }
      }
      "respond succesfully to GET to /Objects/" in {
        Get("/Objects/") ~> myRoute ~> check {
          mediaType === `text/xml`
          status === OK
          responseAs[NodeSeq].head.label === "Objects"
        }
      }
      "respond with error to non existing path" in {
        Get("/Objects/nonexsistent7864057") ~> myRoute ~> check {
          mediaType === `text/xml`
          status === NotFound
          responseAs[NodeSeq].head.label === "error"
        }
      }

      // Somewhat overcomplicated test; Serves as an example for other tests
      "reply its settings as odf from path `settingsOdfPath` (with \"Settings\" id)" in {
        val path = "/" +Path(Starter.settings.settingsOdfPath).toString
        path === "/Objects/OMI-Service/Settings"
        Get(path) ~> myRoute ~> check { // this didn't work without / at start
          status === OK
          mediaType === `text/xml`
          responseAs[NodeSeq] must \("InfoItem", "name" -> "num-latest-values-stored")
          responseAs[NodeSeq] must \("id") \> "Settings"
          //responseAs[NodeSeq] must contain(
          //  <Object><id>Settings</id><InfoItem name="num-latest-values-stored"/></Object>
          //  )
        }
      }
      
//      "handle Read request and respond with xml" in {
//        Post().withEntity(HttpEntity(new ContentType(`text/xml`),"""<?xml version="1.0" encoding="UTF-8"?>
//<omi:omiEnvelope xmlns:omi="omi.xsd" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="omi.xsd omi.xsd" version="1.0" ttl="10">
//  <omi:read msgformat="omi.xsd">
//    <!-- Here could be a list of destination nodes if the message can't be 
//
//sent directly to the destination node(s). -->
//    <omi:msg xmlns="odf.xsd" xsi:schemaLocation="odf.xsd odf.xsd">
//      <Objects>
//        <Object>
//          <id>SmartFridge22334411</id>
//          <InfoItem name="PowerConsumption" />
//        </Object>
//      </Objects>
//    </omi:msg>
//  </omi:read>
//</omi:omiEnvelope>""")) ~> myRoute ~> check {
////          mediaType === `text/xml`
//          responseAs[String] === "test"
//        }
//      }
//
      /** EXAMPLES:

      "return a greeting for GET requests to the root path" in {
        Get() ~> myRoute ~> check {
                  responseAs[String] must contain("Say hello")
        }
      }

      "leave GET requests to other paths unhandled" in {
        Get("/kermit") ~> myRoute ~> check {
                  handled must beFalse
        }
      }

      "return a MethodNotAllowed error for PUT requests to the root path" in {
        Put() ~> sealRoute(myRoute) ~> check {
                  status === MethodNotAllowed
                          responseAs[String] === "HTTP method not allowed, supported methods: GET"
        }
      }
      */
        
    }

}

