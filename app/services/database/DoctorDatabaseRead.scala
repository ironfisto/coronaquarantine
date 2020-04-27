package services.database

import com.google.inject.{Inject, Singleton}
import gremlin.scala._
import models.DoctorModel
import utils.utils
import data.vertexLabels._
import data.propertyLabels.DOCTOR_ID

@Singleton
class DoctorDatabaseRead @Inject()(db: DatabaseConnection,utls:utils) {
  implicit val graph = db.g




  def getDoctorVertex(doctorId:String) ={

    graph.V(utls.createVertexId(DOCTOR,doctorId,DOCTOR_ID)).headOption()

  }

  def getDoctorV(doctorId:String) = {

    graph.V(utls.createVertexId(DOCTOR,doctorId,DOCTOR_ID)).head()
  }

  def toCCDoctorModel(vertex:Vertex) = {

    vertex.toCC[DoctorModel]
  }



}
