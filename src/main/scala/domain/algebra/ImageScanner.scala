package uk.border.control
package domain.algebra

import domain.model.Passport

trait ImageScanner[F[_]] {

  def getPassportFromScannedImage(scannedImage: Array[Byte]): F[Passport]

  def samePersonInBothPictures(image1:Array[Byte], image2:Array[Byte]): F[Boolean]

}
