package uk.border.control
package domain.algebra

import domain.model.{PassportId, CountryCode}

trait EUPassport[F[_]] {

  def checkPassportExist(euCountry: CountryCode, pid:PassportId): F[Boolean]

}
