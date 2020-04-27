package dto.response

import play.api.libs.json.Json


case class patientInfo(name:String,age:Int,gender:String,address:String,districtId:String,stateId:Int,isMigrante:Boolean,isScreened:Boolean,isQuarantined:Boolean,tested:Int,createdDate:java.time.Instant)
object patientInfo{

  implicit val reads = Json.reads[patientInfo]
  implicit val writes = Json.writes[patientInfo]

}


