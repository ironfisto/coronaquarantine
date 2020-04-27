package services.patient

import java.util.UUID.randomUUID

import com.google.inject.{Inject, Singleton}
import dto.requests.{addPatientByDoctor, updateHealthStatus, updatePatientScreen, updateTestReport}
import dto.response.patientInfo
import exceptions.apiExceptions
import services.database.{PatientDatabaseRead, PatientDatabaseWrite}
import services.doctor.DoctorRWservice
import services.user.{UserRequestValidation, UserService}
import utils.utils

@Singleton
class PatientRWservice @Inject()(userService: UserService, patientDatabaseRead: PatientDatabaseRead, patientDatabaseWrite: PatientDatabaseWrite, urv: UserRequestValidation, doctorRWservice: DoctorRWservice, utls: utils) {


  def addPatientByDoctor_CC(dto: addPatientByDoctor) = {

    if (!urv.addPatientByDoctorValidation(dto))
      throw apiExceptions("PLEASE FIX REQUEST")

    userService.userV(dto.patientDetails.user) match {
      case Some(value) => patientDatabaseRead.getPatientVertex(userService.getUserVertexWithCC_DB(value).userId.toString) match {
        case Some(value) => throw apiExceptions("PATIENT ALREADY EXIST")
        case None => throw apiExceptions("YOU ARE DOCTOR")
      }
      //name update
      case None => {

        doctorRWservice.isApprovedAndWithinState(dto.doctorId, dto.patientDetails.user.stateId.toInt) match {
          case true => //if true just update name
            //if false create user in user db then create user in doctor db
            val userId = randomUUID()
            val doctorInfo = doctorRWservice.getDoctorProfile_CC(doctorId = dto.doctorId)
            patientDatabaseWrite.addPatientVertex(dto.patientDetails, userId.toString, dto.doctorId, doctorInfo.name, doctorInfo.phone)
            userService.createUser_DB(dto.patientDetails.user, userId.toString)
          case _ => throw apiExceptions("DOCTOR IS ALLOWED OR NOT REGISTERING PATIENT WITHIN STATE")

        }

      }
    }

  }


  def updatePatientHealthStatus(dto: updateHealthStatus) = {

    if (!utls.isBinary(dto.healthStatus))
      throw apiExceptions("PLEASE GIVE VALID HEALTH STATUS IN BINARY")
    if (!utls.isUuidValid(dto.patientId))
      throw apiExceptions("NOT VALID UUID")


    val patientVertex = patientDatabaseRead.getPatientVertex(dto.patientId) match {
      case Some(value) => value
      case None => throw apiExceptions("NOT VALID UUID")
    }

    patientDatabaseWrite.updateHealthStatus(patientVertex, Integer.parseInt(dto.healthStatus, 2))


  }


  def updatePatientTestReport(dto: updateTestReport) = {

    if (!utls.isTestReport(dto.testReport))
      throw apiExceptions("PLEASE GIVE VALID TEST REPORT")

    if (!doctorRWservice.isDoctorApproved(dto.doctorId))
      throw apiExceptions("DOCTOR NOT APPROVED")

    if (!(utls.isUuidValid(dto.patientId) && utls.isUuidValid(dto.patientId)))
      throw apiExceptions("NOT VALID UUID")

    val patientVertex = patientDatabaseRead.getPatientVertex(dto.patientId) match {
      case Some(value) => value
      case None => throw apiExceptions("NOT VALID UUID")
    }
    patientDatabaseWrite.updateHealthStatus(patientVertex, dto.testReport.toInt)

  }


  def updatePatientScreenReport(dto: updatePatientScreen) = {

    if (!doctorRWservice.isDoctorApproved(dto.doctorId))
      throw apiExceptions("DOCTOR NOT APPROVED")
    if (!(utls.isUuidValid(dto.patientId) && utls.isUuidValid(dto.patientId)))
      throw apiExceptions("NOT VALID UUID")
    if (!doctorRWservice.isDoctorApproved(dto.doctorId))
      throw apiExceptions("DOCTOR NOT APPROVED")
    if (!utls.isValidBoolean(dto.screen))
      throw apiExceptions("PLEASE PROVIDE BOOLEAN")

    val patientVertex = patientDatabaseRead.getPatientVertex(dto.patientId) match {
      case Some(value) => value
      case None => throw apiExceptions("NOT VALID UUID")
    }

    patientDatabaseWrite.updateScreenReport(patientVertex, dto.screen.toBoolean)

  }


  def getPatientInfo_CC(patientId: String) = {

    if (!utls.isUuidValid(patientId))
      throw apiExceptions("NOT VALID UUID")

    patientDatabaseRead.getPatientVertex(patientId) match {
      case Some(value) => {
        val info = patientDatabaseRead.getPatientModel(value)
        patientInfo(name = info.name,
          age = info.age,
          gender = info.gender,
          address = info.address,
          districtId = info.districtId,
          stateId = info.stateId,
          isMigrante = info.isMigrant,
          isScreened = info.isScreened,
          isQuarantined = info.isQuarantined,
          tested = info.tested,
          createdDate = info.createdDate)
      }
      case None => throw apiExceptions("NOT VALID UUID")
    }

  }


}
