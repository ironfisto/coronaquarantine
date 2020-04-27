package dto.requests

import play.api.libs.json.Json

case class addPatientByDoctor(doctorId:String,patientDetails:addPatient)

object addPatientByDoctor {

  implicit val reads = Json.reads[addPatientByDoctor]
  implicit val writes = Json.writes[addPatientByDoctor]

}
