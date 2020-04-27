package dto.requests

import play.api.libs.json.Json

case class isUserExists(districtId: String, stateId: String,phoneNumber: String)
object isUserExists {
  implicit val reads = Json.reads[isUserExists]
  implicit val writes = Json.writes[isUserExists]

}
