package uk.border.control
package domain.model

import java.time.LocalDate

case class Passport
(
  id: PassportId,
  familyName: String,
  givenName: String,
  countryCode: CountryCode,
  dateOfExpiry: LocalDate
)
