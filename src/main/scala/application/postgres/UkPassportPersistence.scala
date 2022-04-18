package uk.border.control
package application.postgres

import domain.algebra.PassportPersistence
import domain.model

import cats.effect.IO

class UkPassportPersistence extends PassportPersistence[IO]{

  override def checkPassportExist(pid: model.PassportId): IO[Boolean] = IO.pure(true)

  override def getPictureInPassport(pid: model.PassportId): IO[Array[Byte]] = IO(Array.emptyByteArray)
}
