package models

import java.util.UUID

import data.propertyLabels._
import data.vertexLabels.DOCTOR
import gremlin.scala._

@label(DOCTOR)
case class DoctorModel(@id doctorId: Option[String] = None,
                       name: String,
                       phone: String,
                       districtId: String,
                       stateId: Int,
                       isApproved: Boolean = false,
                       createdDate: java.time.Instant = java.time.Instant.now(),
                       @underlying vertex: Option[Vertex] = None
                      )

object DoctorModel {

  implicit val marshaller = new Marshallable[DoctorModel] {
    def toCC(element: Element): DoctorModel = {

      DoctorModel(doctorId = Some(element.value[UUID](DOCTOR_ID).toString),
        name = element.value[String](NAME),
        phone = element.value[String](PHONE),
        districtId = element.value[String](DISTRICT_ID),
        stateId = element.value[Int](STATE_ID),
        isApproved = element.value[Boolean](IS_APPROVED),
        createdDate = element.value[java.time.Instant](CREATED_DATE))

    }

    override def fromCC(cc: DoctorModel): FromCC = {
      val doctorId = UUID.fromString(cc.doctorId.get).toString

      val values = Map(DOCTOR_ID -> doctorId,
        NAME -> cc.name,
        PHONE -> cc.phone,
        DISTRICT_ID -> cc.districtId,
        STATE_ID -> cc.stateId,
        IS_APPROVED -> cc.isApproved,
        CREATED_DATE -> cc.createdDate)

      FromCC(Some(doctorId), DOCTOR, values)

    }
  }

}


