package uk.border.control
package application.kafka

import domain.algebra.BorderControlEvents
import domain.model.Passport

import cats.effect.IO
import org.typelevel.log4cats.Logger
import org.typelevel.log4cats.slf4j.Slf4jLogger

import java.time.LocalDateTime

class DummyBorderControlEvents extends BorderControlEvents[IO]{
  implicit def logger: Logger[IO] = Slf4jLogger.getLogger[IO]

  override def publishBorderControlEvent(timeOfEntry: LocalDateTime, passport: Passport): IO[Unit] = {
    Logger[IO].info("Using dummy publisher")
  }
}
