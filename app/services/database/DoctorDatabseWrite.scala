package services.database

import java.util.UUID

import com.google.inject.{Inject, Singleton}
import data.vertexLabels._
import models.DoctorModel
import gremlin.scala._
import data.propertyLabels.{DB_CREATED_DATE, DB_DOCTOR_ID, DB_IS_APPROVED, DB_NAME, DB_PHONE, DB_STATE_ID}
import services.user.UserService
@Singleton
class DoctorDatabseWrite @Inject() (db: DatabaseConnection) {
  implicit val graph =db.g


  def addDoctor(dto:DoctorModel) = {

    graph + dto

  }



}
