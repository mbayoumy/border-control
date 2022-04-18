package uk.border.control
package application.google

import domain.algebra.PersonIdentifier

import cats.effect.IO
import org.typelevel.log4cats.Logger
import org.typelevel.log4cats.slf4j.Slf4jLogger

class CloudVisionApi extends PersonIdentifier[IO] {

  implicit def logger: Logger[IO] = Slf4jLogger.getLogger[IO]

  override def samePersonInBothPictures(image1: Array[Byte], image2: Array[Byte]): IO[Boolean] =
    Logger[IO].info("some sophisticated code checking that both images match the same person") *> IO.pure(true)
}
