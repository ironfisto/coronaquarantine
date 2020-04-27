package services.database


import com.google.inject.Singleton
import javax.inject.Inject
import models.PatientModel
import utils.utils
import data.vertexLabels.PATIENT
import data.propertyLabels._
import gremlin.scala._
@Singleton
class PatientDatabaseRead @Inject()(db: DatabaseConnection,utls:utils) {

  implicit val graph = db.g


  def getPatientVertex(patientId:String) = {

    graph.V(utls.createVertexId(PATIENT,patientId,PATIENT_ID)).headOption()

  }


  def getPatientV(patientId:String) = {

    graph.V(utls.createVertexId(PATIENT,patientId,PATIENT_ID)).head()

  }

  def getPatientModel(vertex:Vertex) ={

    vertex.toCC[PatientModel]
  }


}
