package dto.response

import play.api.libs.json.Json


case class doctorInfo(name:String,phone:String,districtId:String,stateId:String)
object doctorInfo {

    implicit val reads = Json.reads[doctorInfo]
    implicit val writes = Json.writes[doctorInfo]


}
