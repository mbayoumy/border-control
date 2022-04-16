package uk.border.control
package domain.algebra

import domain.model.Passport

trait PassportScanner[F[_]] {

  def getPassportFromScannedImage(scannedImage: Array[Byte]): F[Passport]
}
