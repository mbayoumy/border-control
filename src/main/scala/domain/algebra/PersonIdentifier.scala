package uk.border.control
package domain.algebra

trait PersonIdentifier[F[_]] {

  def samePersonInBothPictures(image1:Array[Byte], image2:Array[Byte]): F[Boolean]

}
