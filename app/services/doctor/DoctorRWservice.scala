package services.doctor

import com.google.inject.{Inject, Singleton}
import data.vertexLabels.DOCTOR
import dto.requests.addDoctor
import dto.response.{doctorInfo, userExistsDto}
import exceptions.apiExceptions
import gremlin.scala._
import models.DoctorModel
import services.database.{DoctorDatabaseRead,DoctorDatabseWrite}
import services.user.{UserRequestValidation, UserService}
import java.util.UUID.randomUUID

import utils.utils

@Singleton
class DoctorRWservice @Inject()(doctorDatabseWrite: DoctorDatabseWrite, doctorDatabaseRead: DoctorDatabaseRead, utls: utils, urv: UserRequestValidation, userService: UserService) {


  /**
   *
   * @param doctorId
   * @return
   */

  def getDoctorProfile_CC(doctorId: String) = {

    if (!utls.isUuidValid(doctorId))
      throw apiExceptions("THIS IS NOT UUID")

    doctorDatabaseRead.getDoctorVertex(doctorId) match {
      case Some(value) => val info = value.toCC[DoctorModel]
        doctorInfo(name = info.name, phone = info.phone, districtId = info.districtId, stateId = info.stateId.toString)
      case None => throw apiExceptions("DOCTOR NOT EXIST")
    }

  }


  /**
   * for doctor registration
   *
   * @param dto
   * @return
   */

  def addDoctorProfile_CC(dto: addDoctor) = {

    //input check
    if(!urv.isUserExist(dto.user))
     throw apiExceptions("FIX JSON")
    userService.userV(dto.user) match {
      case Some(value) =>
        //get userid pass it get doctor
        doctorDatabaseRead.getDoctorVertex(userService.getUserVertexWithCC_DB(value).userId.toString) match {
          case Some(value) => val info = doctorDatabaseRead.toCCDoctorModel(value)
            userExistsDto(true, DOCTOR, Some(info.isApproved))
          case None => throw apiExceptions("YOU ARE PATIENT")
        }
      case None =>
        //if true just update name
        //if false create user in user db then create user in doctor db
        val userId = randomUUID()
        //now register him on
        doctorDatabseWrite.addDoctor(DoctorModel(Some(userId.toString), name = dto.name, phone = dto.user.phoneNumber, districtId = dto.user.districtId, stateId = dto.user.stateId.toInt))
        userService.createUser_DB(dto.user, userId.toString)
        userExistsDto(true, DOCTOR, Some(false))

    }


  }

  def isDoctorApproved(doctorId:String) :Boolean= {

    doctorDatabaseRead.getDoctorVertex(doctorId) match {
      case Some(value) => doctorDatabaseRead.toCCDoctorModel(value).isApproved
      case None => throw apiExceptions("DOCTOR DOES NOT EXIST")
    }

  }

  def isApprovedAndWithinState(doctorId:String,stateId:Int) :Boolean ={
    doctorDatabaseRead.getDoctorVertex(doctorId) match {
      case Some(value) => isDoctorApproved(doctorId) && stateId == doctorDatabaseRead.toCCDoctorModel(value).stateId
      case None => throw apiExceptions("DOCTOR DOES NOT EXIST")
    }

  }


}