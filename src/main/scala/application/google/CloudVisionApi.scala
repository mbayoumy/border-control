package uk.border.control
package application.google

import cats.effect.IO
import org.typelevel.log4cats.Logger
import org.typelevel.log4cats.slf4j.Slf4jLogger
import uk.border.control.domain.algebra.ImageScanner
import uk.border.control.domain.model.CountryCode.GBR
import uk.border.control.domain.model.{Passport, PassportId}

import java.time.LocalDate

class CloudVisionApi extends ImageScanner[IO] {

  implicit def logger: Logger[IO] = Slf4jLogger.getLogger[IO]

  override def samePersonInBothPictures(image1: Array[Byte], image2: Array[Byte]): IO[Boolean] =
    Logger[IO].info("some sophisticated code checking that both images match the same person") *> IO.pure(true)

  override def getPassportFromScannedImage(scannedImage: Array[Byte]): IO[Passport] = {
    Logger[IO].info("using a super sophisticated image scanner") *>
      IO.pure(Passport(
        PassportId("A12345678"),
        "Bayoumy",
        "Mo",
        GBR,
        LocalDate.of(2025,4,13)
      ))
  }
}
