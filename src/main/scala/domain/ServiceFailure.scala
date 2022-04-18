package uk.border.control
package domain

sealed trait ServiceFailure extends Throwable

object ServiceFailure {
  case object PassportExpired extends ServiceFailure
  case object ImageDoesNotMatch extends ServiceFailure
  case object InvalidPassportType extends ServiceFailure
}
