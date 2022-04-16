package uk.border.control
package domain.algebra

import domain.model.PassportId

trait UKPassportPersistence[F[_]] {

  def checkPassportExist(pid:PassportId): F[Boolean]

  def getPictureInPassport(pid:PassportId): F[Array[Byte]]

}
