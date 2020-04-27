package dto.requests

import play.api.libs.json.Json

case class addDoctor(name: String, user: isUserExists)

object addDoctor {

  implicit val reads = Json.reads[addDoctor]
  implicit val writes = Json.writes[addDoctor]

}
