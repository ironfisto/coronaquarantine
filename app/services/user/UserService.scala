package services.user

import com.google.inject.{Inject, Singleton}
import data.vertexLabels._
import dto.requests.isUserExists
import dto.response.userExistsDto
import exceptions.apiExceptions
import gremlin.scala.{_}
import services.database.{DoctorDatabaseRead, UserDatabaseReadWrite}


@Singleton
class UserService @Inject()(urv: UserRequestValidation, userDatabaseReadWrite: UserDatabaseReadWrite, doctorDatabseRead: DoctorDatabaseRead) {


  /**
   *
   * @param dto
   * @return it will check if user is exist in our user, doctor ,patient table
   */

  def userExist_CC(dto: isUserExists) = {


    if (!urv.isUserExist(dto))
      throw apiExceptions("FIX JSON")

    println(userDatabaseReadWrite.isUserExists(dto))

    userDatabaseReadWrite.isUserExists(dto) match {
      case Some(value) => {
        val userId = userDatabaseReadWrite.toCCUserModel(value).userId
        println(userDatabaseReadWrite.getDAndPVertex(userId.toString))
        userDatabaseReadWrite.getDAndPVertex(userId.toString) match {
          case Some(value) => {
            value.label() match {
              case DOCTOR => {
                userExistsDto(true, DOCTOR, Some(doctorDatabseRead.toCCDoctorModel(doctorDatabseRead.getDoctorV(userId.toString)).isApproved))
              }
              case PATIENT => userExistsDto(true, PATIENT)
              case _ => throw apiExceptions("SERVER EXCEPTIONS")
            }

          }

          case None => throw apiExceptions("NOT REGISTERED")
        }
      }
      case None => throw apiExceptions("NOT REGISTERED")
    }

  }

  /**
   *
   * @param dto
   * @return it will return if userId  if user exist in db , for patient no checks , for doctor approved flag check
   *         TODO Need be in Auth
   */

  def getUserId_CC(dto: isUserExists) = {

    if (!urv.isUserExist(dto))
      throw apiExceptions("FIX JSON")

    userDatabaseReadWrite.isUserExists(dto) match {
      case Some(value) => {
        val userId = userDatabaseReadWrite.toCCUserModel(value).userId
        userDatabaseReadWrite.getDAndPVertex(userId.toString) match {
          case Some(value) => {
            value.label() match {
              case DOCTOR => {
                if (doctorDatabseRead.toCCDoctorModel(value).isApproved)
                  userExistsDto(true, DOCTOR, Some(true),Some(userId.toString))
                else {
                  userExistsDto(true, DOCTOR, Some(false))
                }
              }
              case PATIENT => userExistsDto(true, userType = PATIENT,userId = Some(userId.toString))
              case _ => throw apiExceptions("SERVER EXCEPTIONS")
            }

          }
          case None => throw apiExceptions("SERVER EXCEPTIONS")
        }
      }
      case None => userExistsDto(false, "NOT REGISTERED")
    }

  }


  def createUser_DB(dto: isUserExists, userId: String) = {

    userDatabaseReadWrite.addUser(dto, userId)

  }


  /**
   * its sure methods
   *
   * @param dto
   */

  def getUserVertexWithCC_DB(dto: isUserExists) = {

    userDatabaseReadWrite.toCCUserModel(userDatabaseReadWrite.getUserV(dto))

  }



  def getUserVertexWithCC_DB(vertex:Vertex) = {

    vertex.toCC[models.UserModel]

  }



  /**
   * its some none
   *
   * @param id
   * @return
   */

  def getDAndPVertex_DB(id: String) = {

    userDatabaseReadWrite.getDAndPVertex(id)

  }

  /**
   * its sure shot
   *
   * @param id
   * @return Both vertex
   */

  def getDAndPV_DB(id: String) = {

    userDatabaseReadWrite.getDAndPV(id)

  }

  /**
   *
   * @param dto Some values user vertes
   */
  def userV(dto:isUserExists) = {

    userDatabaseReadWrite.isUserExists(dto)

  }


}
