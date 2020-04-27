package data

object covidConstant {

  val TEST_REPORTS :Map[Int,String] = Map(0->"NO",1-> "RA",2->"POSITIVE",3->"NEGATIVE")
  val TEST_REPORTS_RESP :Map[String,String] = Map("0"->"NO","1"-> "RA","2"->"POSITIVE","3"->"NEGATIVE")
  val HEALTH_STATUS :Map[Int,String] = Map(0->"fine".toUpperCase,1->"cough".toUpperCase,2->"shortness of breath".toUpperCase,3->"fever".toUpperCase,4->"critical".toUpperCase)
  val HEALTH_STATUS_RESP :Map[String,String] = Map("0"->"fine".toUpperCase,"1"->"cough".toUpperCase,"2"->"shortness of breath".toUpperCase,"3"->"fever".toUpperCase,"4"->"critical".toUpperCase)

}
