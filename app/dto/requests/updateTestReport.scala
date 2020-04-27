package dto.requests

import play.api.libs.json.Json


case class updateTestReport(doctorId:String,patientId:String,testReport:String)
object updateTestReport {

  implicit val reads = Json.reads[updateTestReport]
  implicit val writes = Json.writes[updateTestReport]

}
