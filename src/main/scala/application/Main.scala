package uk.border.control
package application

import application.google.CloudVisionApi
import application.kafka.DummyBorderControlEvents
import application.postgres.UkPassportRepository
import application.service.EuPassportService
import domain.ElectronicPassportProgram
import domain.ServiceFailure.{ImageDoesNotMatch, InvalidPassport, PassportExpired, RandomSearch}

import cats.effect.{ExitCode, IO, IOApp}
import org.http4s.HttpRoutes
import org.http4s.blaze.server.BlazeServerBuilder
import org.http4s.dsl.io._
import org.typelevel.log4cats.Logger
import org.typelevel.log4cats.slf4j.Slf4jLogger

object Main extends IOApp {
  implicit def logger: Logger[IO] = Slf4jLogger.getLogger[IO]


  override def run(args: List[String]): IO[ExitCode] = {

    val euPassportService = new EuPassportService()
    val ukPassportRepository = new UkPassportRepository()
    val dummyBorderControlEvents = new DummyBorderControlEvents()
    val cloudVisionApi = new CloudVisionApi()

    val electronicPassportProgram = new ElectronicPassportProgram[IO](
      dummyBorderControlEvents,
      euPassportService,
      cloudVisionApi,
      ukPassportRepository
    )

    val resources = for {
      server <- BlazeServerBuilder[IO]
        .bindHttp(8080, "localhost")
        .withHttpApp(router(electronicPassportProgram)).resource
    } yield server

    resources.use(_ => IO.never).as(ExitCode.Success)
  }

  def router(electronicPassportProgram: ElectronicPassportProgram[IO]) = HttpRoutes.of[IO] {
    case POST -> Root / "uk-passport-checker" =>
      Logger[IO].info("calling the service") *>
        electronicPassportProgram
          .enteringTheUk(Array.emptyByteArray, Array.emptyByteArray)
          .flatMap(Ok(_)).handleErrorWith {
          case InvalidPassport => Forbidden("passport is invalid")
          case PassportExpired => Forbidden("passport has expired")
          case ImageDoesNotMatch => InternalServerError("passport picture does not match the photo taken")
          case RandomSearch => NotAcceptable("Looks dodgy, do a random search")
        }
  }.orNotFound
}
