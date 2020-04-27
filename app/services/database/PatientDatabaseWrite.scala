package services.database

import java.util.UUID

import com.google.inject.Singleton
import data.propertyLabels.{DB_HEALTH_STATUS, DB_IS_SCREENED, DB_IS_TESTED, DB_UPDATE_DATE}
import dto.requests.addPatient
import gremlin.scala._
import javax.inject.Inject
import models.PatientModel

@Singleton
class PatientDatabaseWrite @Inject()(db: DatabaseConnection) {

  implicit val graph = db.g


  def addPatientVertex(dto: addPatient, patientId: String, doctorId: String, doctorName: String, doctorPhone: String) = {

    graph + PatientModel(patientId = Some(patientId),
      name = dto.name,
      age = dto.age.toInt,
      gender = dto.gender,
      phone = dto.user.phoneNumber,
      stateId = dto.user.stateId.toInt,
      districtId = dto.user.districtId,
      address = dto.address,
      doctorId = UUID.fromString(doctorId),
      doctorName = doctorName,
      doctorPhone = doctorPhone,
      healthStatus = Integer.parseInt(dto.healthStatus.getOrElse("10000"), 2),
      isMigrant = dto.isMigrant.getOrElse("false").toBoolean,
      isScreened = dto.isScreened.getOrElse("false").toBoolean,
      isQuarantined = true)

  }



  def updateHealthStatus(vertex: Vertex, healthStatus: Int) = {

    graph.V(vertex).property(KeyValue(DB_HEALTH_STATUS, healthStatus)).property(KeyValue(DB_UPDATE_DATE, java.time.Instant.now())).iterate()


  }



  def updateTestReport(vertex: Vertex, testReport: Int) = {

    graph.V(vertex).property(KeyValue(DB_IS_TESTED, testReport)).property(KeyValue(DB_UPDATE_DATE, java.time.Instant.now())).iterate()


  }

  def updateScreenReport(vertex: Vertex, screen: Boolean) = {

    graph.V(vertex).property(KeyValue(DB_IS_SCREENED, screen)).property(KeyValue(DB_UPDATE_DATE, java.time.Instant.now())).iterate()


  }
}
