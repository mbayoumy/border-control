package uk.border.control
package domain

package object model {

  case class PassportId(value:String)

  sealed trait CountryCode

  object CountryCode {
    case object GBR extends CountryCode
    case object ESP extends CountryCode
    case object FRA extends CountryCode
    case object EGY extends CountryCode
  }

}
