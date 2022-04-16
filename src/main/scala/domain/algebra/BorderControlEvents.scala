package uk.border.control
package domain.algebra

import domain.model.Passport

import java.time.LocalDateTime

trait BorderControlEvents[F[_]] {

  def publishBorderControlEvent(timeOfEntry:LocalDateTime, passport: Passport): F[Unit]

}
