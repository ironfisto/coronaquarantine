package data

import com.datastax.driver.dse.geometry.Point
import java.util.UUID
import gremlin.scala.Key

object propertyLabels {

  val PHONE = "phone"
  val DB_PHONE = Key[String](PHONE)
  val DISTRICT_ID = "districtId"
  val DB_DISTRICT_ID = Key[String](DISTRICT_ID)
  val STATE_ID = "stateId"
  val DB_STATE_ID = Key[Int](STATE_ID)
  val USER_ID = "userId"
  val DB_USER_ID = Key[UUID](USER_ID)
  val CREATED_DATE = "createdDate"
  val DB_CREATED_DATE = Key[java.time.Instant](CREATED_DATE)
  val DOCTOR_ID = "doctorId"
  val DB_DOCTOR_ID =Key[UUID](DOCTOR_ID)
  val NAME = "name"
  val DB_NAME = Key[String](NAME)
  val IS_APPROVED = "isApproved"
  val DB_IS_APPROVED = Key[Boolean](IS_APPROVED)
  val PATIENT_ID ="patientId"
  val DB_PATIENT_ID = Key[UUID](PATIENT_ID)
  val AGE = "age"
  val DB_AGE = Key[Int](AGE)
  val GENDER = "gender"
  val DB_GENDER = Key[String](GENDER)
  val ADDRESS = "address"
  val DB_ADDRESS = Key[String](ADDRESS)
  val HEALTH_STATUS = "healthStatus"
  val DB_HEALTH_STATUS = Key[Int](HEALTH_STATUS)
  val UPDATED_DATE ="updatedDate"
  val DB_UPDATE_DATE =Key[java.time.Instant](UPDATED_DATE)
  val TESTED ="tested"
  val DB_IS_TESTED = Key[Int](TESTED)
  val DOCTOR_NAME = "doctorName"
  val DB_DOCTOR_NAME = Key[String](DOCTOR_NAME)
  val DOCTOR_PHONE = "doctorPhone"
  val DB_DOCTOR_PHONE = Key[String](DOCTOR_PHONE)
  val LOCATION = "location"
  val DB_LOCATION = Key[Point](LOCATION)
  val IS_QUARANTINED = "isQuarantined"
  val DB_IS_QUARANTINED = Key[Boolean](IS_QUARANTINED)
  val IS_MIGRANT = "isMigrant"
  val DB_IS_MIGRANT = Key[Boolean](IS_MIGRANT)
  val IS_SCREENED = "isScreened"
  val DB_IS_SCREENED = Key[Boolean](IS_SCREENED)

}
