package services.user

import com.google.inject.{Inject, Singleton}
import dto.requests.{addPatient, addPatientByDoctor, isUserExists}
import exceptions.apiExceptions
import utils.utils


@Singleton
class UserRequestValidation @Inject()(utls: utils) {


  def addPatientByDoctorValidation(dto: addPatientByDoctor): Boolean = {

    val userDetail: isUserExists = dto.patientDetails.user
    val doctorId = dto.doctorId
    val patientDetails = dto.patientDetails

    if (!utls.isUuidValid(doctorId))
      throw apiExceptions("NOT VALID UUID")


    isUserExist(userDetail) && addPatentValidation(patientDetails)
  }

  def isUserExist(dto: isUserExists): Boolean = {
    val request = dto

    if (!utls.isStateIdValid(request.stateId))
      throw apiExceptions(s"WRONG ${request.stateId}")
    if (!utls.isValidDistrictId(request.districtId))
      throw apiExceptions(s"WRONG ${request.districtId}")
    if (!utls.isDistrictIdWithinStateId(request.districtId, request.stateId.toInt))
      throw apiExceptions(s"${request.districtId} Is Not Within ${request.stateId}")

    if (!utls.isPhoneNumberValid(request.phoneNumber))
      throw apiExceptions(s"WRONG PHONE NO. ${request.phoneNumber}")
    true
  }

  def addPatentValidation(dto: addPatient) = {


    if (!utls.isValidInt(dto.age))
      throw apiExceptions("AGE NOT INT")
    if (!(dto.healthStatus.isDefined && utls.isBinary(dto.healthStatus.get)))
      throw apiExceptions("PLEASE ENTER VALID HEALTH STATUS")

    if (!(dto.isMigrant.isDefined && utls.isValidBoolean(dto.isMigrant.get)))
      throw apiExceptions("PLEASE FIX BOOLEAN")

    if (!(dto.isScreened.isDefined && utls.isValidBoolean(dto.isScreened.get)))
      throw apiExceptions("PLEASE FIX BOOLEAN ")

    true

  }

}
