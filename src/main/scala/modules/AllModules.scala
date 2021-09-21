package modules

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.softwaremill.macwire._
import routes.{Endpoints, GreetingRoute, HealthCheckRoute}
import scala.concurrent.ExecutionContext

class AllModules extends EndpointModules

trait EndpointModules extends AkkaModules {
  lazy val healthCheckEndpoint = wire[HealthCheckRoute]
  lazy val greetingEndpoint = wire[GreetingRoute]

  lazy val endpoints = wire[Endpoints]
}
trait AkkaModules {
  implicit lazy val system = ActorSystem("simpleHttpServerJson")
  implicit lazy val materializer = ActorMaterializer()
  implicit lazy val executor: ExecutionContext = system.dispatcher
}