package dto.requests

import play.api.libs.json.Json

case class addPatient(user:isUserExists, name:String, age:String, gender:String,address:String, isMigrant:Option[String]=Some("false"), isScreened:Option[String]=Some("false"), healthStatus:Option[String]=Some("10000"))

object addPatient {


  implicit val reads = Json.reads[addPatient]
  implicit val writes = Json.writes[addPatient]

}
