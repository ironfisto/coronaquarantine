package dto.requests

import play.api.libs.json.Json

case class updatePatientScreen(doctorId: String, patientId: String, screen: String)

object updatePatientScreen {

  implicit val reads = Json.reads[updatePatientScreen]
  implicit val writes = Json.writes[updatePatientScreen]

}
