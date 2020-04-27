package dto.response

import play.api.libs.json.Json

case class nameIdDto(id:String,name:String)
object nameIdDto{

  implicit val reads = Json.reads[nameIdDto]
  implicit val writes = Json.writes[nameIdDto]
}

