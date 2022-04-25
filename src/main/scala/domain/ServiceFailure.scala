package uk.border.control
package domain

sealed trait ServiceFailure extends Throwable

object ServiceFailure {
  case object PassportExpired extends ServiceFailure
  case object ImageDoesNotMatch extends ServiceFailure
  case object InvalidPassport extends ServiceFailure
  case object RandomSearch extends ServiceFailure
}
