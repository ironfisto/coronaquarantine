package models

import java.util.UUID

import data.propertyLabels._
import data.vertexLabels.USER
import gremlin.scala._

@label(USER)
case class UserModel(@id phone: String,
                     @id stateId: Int,
                     @id districtId: String,
                     userId: UUID,
                     createdDate: java.time.Instant = java.time.Instant.now(),
                     @underlying vertex: Option[Vertex] = None)

object UserModel {

  implicit val marshaller = new Marshallable[UserModel] {
    def toCC(element: Element): UserModel = {
      val phone = element.value[String](PHONE)
      val dId = element.value[String](DISTRICT_ID)
      val sId = element.value[Int](STATE_ID)
      val userId = element.value[UUID](USER_ID)
      val createdDate =
        element.value[java.time.Instant](CREATED_DATE)

      UserModel(phone = phone,
        districtId = dId,
        stateId = sId,
        userId = userId,
        createdDate = createdDate)
    }

    override def fromCC(cc: UserModel): FromCC = {
      val values = Map(
        PHONE -> cc.phone,
        DISTRICT_ID -> cc.districtId,
        STATE_ID -> cc.stateId,
        CREATED_DATE -> cc.createdDate,
        USER_ID -> cc.userId
      )
      FromCC(Some(PHONE, DISTRICT_ID, STATE_ID), USER, values)
    }
  }
}
