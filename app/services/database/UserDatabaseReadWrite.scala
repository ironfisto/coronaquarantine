package services.database

import java.util.UUID

import com.google.inject.{Inject, Singleton}
import data.vertexLabels.{DOCTOR, PATIENT, USER}
import data.propertyLabels.{DB_DISTRICT_ID, DB_PHONE, DB_STATE_ID, DB_USER_ID, DOCTOR_ID, PATIENT_ID,DB_CREATED_DATE}
import dto.requests.isUserExists
import gremlin.scala._
import utils.utils
import models.UserModel

@Singleton
class UserDatabaseReadWrite @Inject() (db : DatabaseConnection,utls:utils) {

  implicit val graph = db.g

  def isUserExists(dto:isUserExists) = {

    graph.V(utls.createUserVertexId(dto.phoneNumber,dto.districtId,dto.stateId.toInt)).headOption()

  }


  def getUserV(dto:isUserExists) ={

    isUserExists(dto).head

  }


  def toCCUserModel(vertex:Vertex):UserModel ={

    vertex.toCC[UserModel]

  }


  def getDAndPVertex(id:String)={

    graph.V(utls.createVertexId(DOCTOR,id,DOCTOR_ID),utls.createVertexId(PATIENT,id,PATIENT_ID)).headOption()

  }

  def getDAndPV(id:String)={

    graph.V(utls.createVertexId(DOCTOR,id,DOCTOR_ID),utls.createVertexId(PATIENT,id,PATIENT_ID)).head()

  }


  /**
   *
   * @param dto
   * @param userId
   * @return it add user in user vertex
   */

  def addUser(dto:isUserExists,userId:String) = {
    graph.addV(USER)
      .property(KeyValue(DB_USER_ID,UUID.fromString(userId)))
      .property(KeyValue(DB_PHONE,dto.phoneNumber))
      .property(KeyValue(DB_STATE_ID,dto.stateId.toInt))
      .property(KeyValue(DB_DISTRICT_ID,dto.districtId))
      .property(KeyValue(DB_CREATED_DATE,java.time.Instant.now()))
      .iterate()
  }


}
