package uk.border.control
package domain

import domain.ServiceFailure.{ImageDoesNotMatch, PassportExpired}
import domain.algebra._
import domain.model.CountryCode._
import domain.model.Passport

import cats.effect.kernel.Sync
import cats.implicits._

import java.time.{LocalDate, LocalDateTime}


class ElectronicPassportProgram[F[_]]
(
 borderControlEvents: BorderControlEvents[F],
 euPassport: EUPassport[F],
 passportScanner: PassportScanner[F],
 ukPassportPersistence: UKPassportPersistence[F],
 personIdentifier: PersonIdentifier[F]
)(implicit M: Sync[F]) {

  def enteringTheUk(scannedPassport: Array[Byte], personImage: Array[Byte]): F[Unit] = {
    for {
      passport <- passportScanner.getPassportFromScannedImage(scannedPassport)
      _ <- validatePassport(passport)
      _ <- checkPassportExpiry(passport)
      passportPicture <- ukPassportPersistence.getPictureInPassport(passport.id)
      _ <- personIdentifier.samePersonInBothPictures(passportPicture, personImage)
      _ <- borderControlEvents.publishBorderControlEvent(LocalDateTime.now(), passport)
    } yield ()
  }

   def validatePassport(passport: Passport): F[Boolean] = {
    passport.countryCode match {
      case EGY => M.raiseError
      case GBR => ukPassportPersistence.checkPassportExist(passport.id)
      case ESP | FRA => euPassport.checkPassportExist(passport.countryCode, passport.id)
    }
  }

  private def checkPassportExpiry(passport: Passport): F[Unit] = {
    passport.dateOfExpiry.isAfter(LocalDate.now()) match {
      case true => M.unit
      case false => M.raiseError(PassportExpired)
    }
  }

}
