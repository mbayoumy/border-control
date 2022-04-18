package uk.border.control
package application.service

import domain.algebra.EuPassport
import domain.model

import cats.effect.IO

class EuPassportService extends EuPassport[IO] {
  override def checkPassportExist(euCountry: model.CountryCode, pid: model.PassportId): IO[Boolean] = IO.pure(true)
}
