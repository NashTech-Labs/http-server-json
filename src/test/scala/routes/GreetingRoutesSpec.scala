package routes
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.ScalatestRouteTest
import modules.AllModules
import org.scalatest.{FeatureSpec, Matchers}

  class GreetingRoutesSpec
    extends FeatureSpec
      with Matchers
      with ScalatestRouteTest {

    val modules = new AllModules
    val route = modules.endpoints.routes

    feature("greeting api") {
      scenario("successful get") {
        Get(s"/api/greetings?name=gabriel&greeting=hello") ~> route ~> check {
          status shouldBe StatusCodes.OK
          responseAs[String] should include(s"hello, gabriel")
        }
      }

      scenario("unprocessable entity get") {
        Get(s"/api/greetings?name=gabriel&greeting=") ~> route ~> check {
          status shouldBe StatusCodes.BadRequest
        }
      }
    }
  }


