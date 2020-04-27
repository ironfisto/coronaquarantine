package models

import java.util.UUID

import data.propertyLabels._
import data.vertexLabels.PATIENT
import gremlin.scala._

@label(PATIENT)
case class PatientModel(@id patientId: Option[String] = None,
                        name: String,
                        age: Int,
                        gender: String,
                        phone: String,
                        stateId: Int,
                        districtId: String,
                        address: String,
                        doctorId: UUID,
                        doctorName: String,
                        doctorPhone: String,
                        healthStatus: Int,
                        tested: Int=0,
                        isMigrant: Boolean,
                        isScreened: Boolean,
                        isQuarantined: Boolean,
                        createdDate: java.time.Instant = java.time.Instant.now(),
                        updatedDate: java.time.Instant =java.time.Instant.now(),
                        @underlying vertex: Option[Vertex] = None
                       )


object PatientModel {

  implicit val marshaller = new Marshallable[PatientModel] {

    def toCC(element: Element): PatientModel = {

      PatientModel(patientId = Some(element.value[UUID](PATIENT_ID).toString),
        name = element.value[String](NAME),
        age = element.value[Int](AGE),
        gender = element.value[String](GENDER),
        phone = element.value[String](PHONE),
        stateId = element.value[Int](STATE_ID),
        districtId = element.value[String](DISTRICT_ID),
        address = element.value[String](ADDRESS),
        doctorId = element.value[UUID](DOCTOR_ID),
        doctorName = element.value[String](DOCTOR_NAME),
        doctorPhone = element.value[String](DOCTOR_PHONE),
        healthStatus = element.value[Int](HEALTH_STATUS),
        tested = element.value[Int](TESTED),
        isMigrant = element.value[Boolean](IS_MIGRANT),
        isScreened = element.value[Boolean](IS_SCREENED),
        isQuarantined = element.value[Boolean](IS_QUARANTINED),
        createdDate = element.value[java.time.Instant](CREATED_DATE),
        updatedDate = element.value[java.time.Instant](UPDATED_DATE))

    }

    override def fromCC(cc: PatientModel): FromCC = {
      val patientId = UUID.fromString(cc.patientId.get).toString
      val values = Map(PATIENT_ID -> patientId,
        NAME -> cc.name,
        AGE -> cc.age,
        GENDER -> cc.gender,
        PHONE -> cc.phone,
        STATE_ID -> cc.stateId,
        DISTRICT_ID -> cc.districtId,
        ADDRESS -> cc.address,
        DOCTOR_ID -> cc.doctorId,
        DOCTOR_NAME -> cc.doctorName,
        DOCTOR_PHONE->cc.doctorPhone,
        HEALTH_STATUS -> cc.healthStatus,
        TESTED -> cc.tested,
        IS_MIGRANT -> cc.isMigrant,
        IS_SCREENED -> cc.isScreened,
        IS_QUARANTINED -> cc.isQuarantined,
        CREATED_DATE -> cc.createdDate,
        UPDATED_DATE -> cc.updatedDate
      )

      FromCC(Some(patientId), PATIENT, values)
    }

  }

}
