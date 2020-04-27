package controllers.doctorControllers

import com.google.inject.{Inject, Singleton}
import data.vertexLabels.DOCTOR
import dto.requests.{addDoctor,addPatientByDoctor}
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, ControllerComponents}
import services.doctor.DoctorRWservice
import services.patient.PatientRWservice
import utils.utils

import scala.util.{Failure, Success, Try}

@Singleton
class DoctorController @Inject()(cc: ControllerComponents, utls: utils, doctorRWservice: DoctorRWservice, patientRWservice: PatientRWservice) extends AbstractController(cc) {


  def registerDoctor() = Action(parse.json) { request =>


    Try {

      val dto = Json.fromJson[addDoctor](request.body)
      if (dto.isError) {
        throw exceptions.apiExceptions("Malformed Json. Double check the fields")
      }

      else {
        doctorRWservice.addDoctorProfile_CC(dto.get)
      }

    } match {
      case Success(value) => Ok(utls.apiResponse("success",Json.toJson[dto.response.userExistsDto](value),"done"))
      case Failure(exception) => Ok(utls.apiResponse("failed",utls.errResp(400, DOCTOR, exception.getMessage),"done"))
    }


  }


  def getDoctorProfile(doctorId: String) = Action {

    Try {

      doctorRWservice.getDoctorProfile_CC(doctorId)


    } match {
      case Success(value) => Ok(utls.apiResponse("success",Json.toJson[dto.response.doctorInfo](value),"done"))
      case Failure(exception) => Ok(utls.apiResponse("failed",utls.errResp(400, DOCTOR, exception.getMessage),"done"))
    }

  }


  def registerPatient() = Action(parse.json) { request =>

    Try {

      val dto = Json.fromJson[addPatientByDoctor](request.body)
      if (dto.isError) {
        throw exceptions.apiExceptions("Malformed Json. Double check the fields")
      }

      else {
        patientRWservice.addPatientByDoctor_CC(dto.get)
      }


    } match {
      case Success(value) =>Ok(utls.apiResponse("success",utls.errResp(200, DOCTOR, "DOCTOR"),"done"))
      case Failure(exception) => Ok(utls.apiResponse("failed",utls.errResp(400, DOCTOR, exception.getMessage),"done"))
    }

  }

}
