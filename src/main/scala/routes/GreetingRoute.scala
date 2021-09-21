package routes
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import response.{Greeting, Response}

class GreetingRoute {
  val greetingRoute: Route = pathPrefix("api" / "greetings") {
    (get & parameters("greeting", "name").as(Greeting)) { greeting =>
      complete(StatusCodes.OK -> Response(greeting.greet))
    }
  }
}
