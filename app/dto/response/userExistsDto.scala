package dto.response

import play.api.libs.json.Json


/**
 *
 * @param exist
 * @param userType
 */
case class userExistsDto(exist:Boolean, userType:String,approved:Option[Boolean]=None,userId:Option[String]=None)

object userExistsDto {
  implicit val reads = Json.reads[userExistsDto]
  implicit val writes = Json.writes[userExistsDto]
}
