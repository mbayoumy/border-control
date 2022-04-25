package uk.border.control
package domain

import domain.ServiceFailure.{ImageDoesNotMatch, InvalidPassport, PassportExpired, RandomSearch}
import domain.algebra._
import domain.model.CountryCode._
import domain.model.Passport

import cats.effect.kernel.Sync
import cats.implicits._

import java.time.{LocalDate, LocalDateTime}


class ElectronicPassportProgram[F[_]]
(
  borderControlEvents: BorderControlEvents[F],
  euPassport: EuPassport[F],
  imageScanner: ImageScanner[F],
  ukPassportPersistence: PassportRepository[F]
)(implicit M: Sync[F]) {

  def enteringTheUk(scannedPassport: Array[Byte], personImage: Array[Byte]): F[Unit] = {
    for {
      passport <- imageScanner.getPassportFromScannedImage(scannedPassport)
      _ <- validatePassport(passport)
      _ <- checkPassportExpiry(passport)
      passportPicture <- ukPassportPersistence.getPictureInPassport(passport.id)
      _ <- imageScanner.samePersonInBothPictures(passportPicture, personImage)
      _ <- borderControlEvents.publishBorderControlEvent(LocalDateTime.now(), passport)
    } yield ()
  }

   private def validatePassport(passport: Passport): F[Unit] = {
    val r: F[_ <: Boolean] = passport.countryCode match {
      case EGY => M.raiseError(RandomSearch)
      case GBR => ukPassportPersistence.checkPassportExist(passport.id)
      case ESP | FRA => euPassport.checkPassportExist(passport.countryCode, passport.id)
    }
     r.flatMap {
       case true => M.unit
       case false => M.raiseError(InvalidPassport)
     }
  }

  private def checkPassportExpiry(passport: Passport): F[Unit] = {
    passport.dateOfExpiry.isAfter(LocalDate.now()) match {
      case true => M.unit
      case false => M.raiseError(PassportExpired)
    }
  }

}
