package uk.border.control
package application.service

import domain.algebra.PassportScanner
import domain.model.CountryCode.GBR
import domain.model.{Passport, PassportId}

import cats.effect.IO
import org.typelevel.log4cats.Logger
import org.typelevel.log4cats.slf4j.Slf4jLogger

import java.time.LocalDate

class SuperSophisticatedImageScanner extends PassportScanner[IO] {

  implicit def logger: Logger[IO] = Slf4jLogger.getLogger[IO]

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
