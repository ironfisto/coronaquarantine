package utils

import controllers.{ErrorRes, Response}
import data.data.{MAP_OF_STATE, STATE_DIST}
import data.covidConstant.TEST_REPORTS
import play.api.libs.json.{JsValue, Json}

import scala.util.{Failure, Success, Try}

class utils {


  /**
   *
   * @param data
   * @return
   */
  def isBinary(data: String): Boolean = {
    Try(data.size == 5 && data.matches("[01]{5}+")) match {
      case Success(v) => v
      case Failure(e) => false
    }
  }

  def isTestReport(data:String) :Boolean = {

    Try(data.size==1 && isValidInt(data) && TEST_REPORTS.exists(x=>x._1==data.toInt)) match {
      case Success(value) => true
      case Failure(exception) => false
    }
  }


  /**
   *
   * @param data
   * @return
   */

  def isPhoneNumberValid(data: String): Boolean = {
    Try(data.matches("^(9|8|7|6)[0-9]{9}$")) match {
      case Success(v) => v
      case Failure(exception) => false
    }
  }

  def isUuidValid(data: String): Boolean = {

    Try(data.matches("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$")) match {
      case Success(value) => value
      case Failure(exception) => false
    }
  }

  def isValidDistrictId(data: String): Boolean = {

    Try(data.matches("^(RJ|GJ)[0-9]{2}$")) match {
      case Success(value) => value
      case Failure(exception) => false
    }
  }


  def isStateIdValid(data: String): Boolean = {

    Try((isValidInt(data) && data.matches("^[0-9]{1}$|^[1|2]{1}[0-9]{1}$|^[3]{1}[0-6]{1}$") && MAP_OF_STATE.exists(x => x._1 == data.toInt))) match {
      case Success(value) => value
      case Failure(exception) => false
    }

  }

  def isValidInt(data: String): Boolean = {

    Try(data.toInt.isInstanceOf[Int]) match {
      case Success(v) => true
      case Failure(e) => false
    }

  }


  def isValidBoolean(data:String) :Boolean = {

    Try(data.toBoolean.isInstanceOf[Boolean]) match {
      case Success(v) => true
      case Failure(exception) => false
    }
  }

  /**
   *
   * @param dId :districtId
   * @param sId :stateId
   * @return It will retun if district is within state
   */

  def isDistrictIdWithinStateId(dId: String, sId: Int): Boolean = {

    Try(STATE_DIST.get(MAP_OF_STATE.get(sId).get).get.exists(x => x._1 == dId)) match {
      case Success(value) => value
      case Failure(exception) => false
    }


  }


  /**
   * for error response
   *
   * @param code
   * @param module
   * @param message
   * @return
   */

  def errResp(code: Int, module: String, message: String) = {
    val err = ErrorRes(module, message)
    val errorResp = Response(code, Json.toJson[ErrorRes](err))
    Json.toJson(errorResp)
  }


  def apiResponse(status: String, module: JsValue, message: String) = {

    val errorResp = dto.apiResponse(message = message,status = status, module)
    Json.toJson(errorResp)

  }


  /**
   *
   * @param phone
   * @param districtId
   * @param stateId
   * @return this is for creating vertex id of services.user
   */

  def createUserVertexId(phone: String, districtId: String, stateId: Int) = {
    val vertexId =
      new java.util.LinkedHashMap[String, Any]()
    val vLabel =
      vertexId.put("~label", data.vertexLabels.USER)
    val dId =
      vertexId.put(data.propertyLabels.DISTRICT_ID,districtId)
    val phoneNumber =
      vertexId.put(data.propertyLabels.PHONE,phone)
    val sId = vertexId.put(data.propertyLabels.STATE_ID, stateId)
    vertexId
  }

  /**
   *
   * @param vertexLabel
   * @param id
   * @param propertyLabels
   * @return createing docker and patient vertex id
   */


  def createVertexId(vertexLabel: String, id: String, propertyLabels: String) = {

    val vertexId =
      new java.util.LinkedHashMap[String, Any]()
    val vLabel =
      vertexId.put("~label", vertexLabel)
    val vId =
      vertexId.put(propertyLabels, id)

    vertexId
  }


}
