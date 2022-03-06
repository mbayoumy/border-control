package uk.border.control
package application

import cats.effect.{ExitCode, IO, IOApp}
import org.http4s.HttpRoutes
import org.http4s.blaze.server.BlazeServerBuilder
import org.http4s.dsl.io._

object Main extends IOApp {
  override def run(args: List[String]): IO[ExitCode] = {
    def helloWorldService() = HttpRoutes.of[IO] {
      case GET -> Root / "hello" =>
        Ok("hello world")
    }.orNotFound

    val resources = for {
      server <- BlazeServerBuilder[IO]
        .bindHttp(8080, "localhost")
        .withHttpApp(helloWorldService()).resource
    } yield server

    resources.use(_ => IO.never).as(ExitCode.Success)
  }
}
