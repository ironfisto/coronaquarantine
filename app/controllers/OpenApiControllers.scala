package controllers

import com.google.inject.{Inject, Singleton}
import data.covidConstant.{HEALTH_STATUS_RESP, TEST_REPORTS_RESP}
import data.data.{MAP_OF_STATE_RESP, STATE_DIST}
import data.vertexLabels._
import dto.requests.isUserExists
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, ControllerComponents}
import services.patient.PatientRWservice
import services.user.UserService
import utils.utils

import scala.util.{Failure, Success, Try}


@Singleton
class OpenApiControllers @Inject()(cc: ControllerComponents, patientRWservice: PatientRWservice, utls: utils, userService: UserService) extends AbstractController(cc) {


  /**
   *
   * @param stateName
   * @return get all names of districts in states
   */

  def getDistrictsDto(stateName: Option[String]) = Action {

    Try(STATE_DIST.get(stateName.get.toLowerCase()).get) match {
      case Success(value) => var districtsId =  List[dto.response.nameIdDto]()
        value.toList.map(x=>{
          districtsId ::= dto.response.nameIdDto(id =x ._1,name= x._2)
        })
        Ok(utls.apiResponse("success", Json.toJson(districtsId.sortBy(x=>x.id)), "done"))
      case Failure(exception) => NotFound(utls.errResp(400, USER, exception.getMessage))
    }


  }

  /**
   *
   * @return get all states name
   */

  def getStateDto() = Action {

    var stateId =  List[dto.response.nameIdDto]()
    MAP_OF_STATE_RESP.toList.map(x=>{
      stateId ::= dto.response.nameIdDto(id =x ._1,name= x._2)
    })


    Ok(utls.apiResponse("success", Json.toJson(stateId.sortBy(x=> x.id)), "done"))

  }

  /**
   *
   * @return will health status dto
   */


  def getHealthStatusDto() = Action {

    var healthStatus =  List[dto.response.nameIdDto]()
    HEALTH_STATUS_RESP.toList.map(x=>{
      healthStatus ::= dto.response.nameIdDto(id =x ._1,name= x._2)
    })

    Ok(utls.apiResponse("success", Json.toJson(healthStatus.sortBy(x=>x.id)), "done"))

  }

  /**
   *
   * @return will return test reports dto
   */

  def getTestReportsDto() = Action {

    var testReports =  List[dto.response.nameIdDto]()
    TEST_REPORTS_RESP.toList.map(x=>{
      testReports ::= dto.response.nameIdDto(id =x ._1,name= x._2)
    })

    Ok(utls.apiResponse("success", Json.toJson(testReports.sortBy(x=>x.id)), "done"))

  }


  /**
   *
   * @return this method is for checking if services.user exist in our services.services.database
   */
  def isUserExist() = Action(parse.json) { request =>


    Try {
      val dto = Json.fromJson[isUserExists](request.body)
      if (dto.isError) {
        throw exceptions.apiExceptions("Malformed Json. Double check the fields")
      }

      else {
        userService.userExist_CC(dto.get)
      }
    } match {
      case Success(value) => Ok(utls.apiResponse("success", Json.toJson[dto.response.userExistsDto](value), "done"))
      case Failure(exception) => Ok(utls.apiResponse("failed", utls.errResp(400, USER, exception.getMessage), "done"))
    }

  }

  /**
   *
   * @return to get userId of doctor and patientId
   */

  def getUserId() = Action(parse.json) { request =>
    
    Try {
      val dto = Json.fromJson[isUserExists](request.body)
      if (dto.isError) {
        throw exceptions.apiExceptions("Malformed Json. Double check the fields")
      }

      else {
        userService.getUserId_CC(dto.get)
      }
    } match {
      case Success(value) => Ok(utls.apiResponse("success", Json.toJson[dto.response.userExistsDto](value), "done"))
      case Failure(exception) => Ok(utls.apiResponse("failed", utls.errResp(400, USER, exception.getMessage), "done"))
    }

  }

  def getPatientProfile(patientId: String) = Action {

    Try {

      patientRWservice.getPatientInfo_CC(patientId)

    } match {
      case Success(value) => Ok(utls.apiResponse("success", Json.toJson[dto.response.patientInfo](value), "done"))
      case Failure(exception) => Ok(utls.apiResponse("failed", utls.errResp(400, USER, exception.getMessage), "done"))
    }

  }


}
