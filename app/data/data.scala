package data

object data {
  /**
   * states name
   */
  val STATE_RAJASTHAN :String= "rajasthan".toLowerCase()
  val STATE_GUJARAT :String ="Gujarat".toLowerCase()


  /**
   *  State List
   */

  val MAP_OF_STATE = Map(1->STATE_RAJASTHAN,2->STATE_GUJARAT)
  val MAP_OF_STATE_RESP = Map("1"->STATE_RAJASTHAN,"2"->STATE_GUJARAT)


  /**
   * dist names
   */

  val DIST_RAJASTHAN:Map[String,String] =Map("RJ01"->"Ajmer".toLowerCase(),
    "RJ02" -> "Alwar".toLowerCase(),
    "RJ03" -> "Banswara".toLowerCase(),
    "RJ04" ->"Barmer".toLowerCase(),
    "RJ05" ->"Bharatpur".toLowerCase(),
    "RJ06" ->"Bhilwara".toLowerCase(),
    "RJ07" ->"Bikaner".toLowerCase(),
    "RJ08" ->"Bundi".toLowerCase(),
    "RJ09" ->"Chittaurgarh".toLowerCase(),
    "RJ10" ->"Churu".toLowerCase(),
    "RJ11" ->"Dholpur".toLowerCase,
    "RJ12" ->"Dungarpur".toLowerCase(),
    "RJ13" ->"Sri Ganganagar".toLowerCase(),
    "RJ14" ->"Jaipur".toLowerCase(),
    "RJ15" ->"Jaisalmer".toLowerCase(),
    "RJ16" ->"Jalore".toLowerCase(),
    "RJ17" ->"Jhalawar".toLowerCase(),
    "RJ18" ->"Jhunjhunu".toLowerCase(),
    "RJ19" ->"Jodhpur".toLowerCase(),
    "RJ20" ->"Kota".toLowerCase(),
    "RJ21" ->"Nagaur".toLowerCase(),
    "RJ22" ->"Pali".toLowerCase(),
    "RJ23" ->"Sikar".toLowerCase(),
    "RJ24" ->"Sirohi".toLowerCase(),
    "RJ25" ->"Sawai Madhopur".toLowerCase(),
    "RJ26" ->"Tonk".toLowerCase(),
    "RJ27" ->"Udaipur".toLowerCase(),
    "RJ28" ->"Baran".toLowerCase(),
    "RJ29" ->"Dausa".toLowerCase(),
    "RJ30" ->"Rajsamand".toLowerCase(),
    "RJ31" ->"Hanumangarh".toLowerCase(),
    "RJ32" ->"Kotputli".toLowerCase(),
    "RJ33" ->"Ramganj Mandi".toLowerCase(),
    "RJ34" ->"Karauli".toLowerCase(),
    "RJ35" ->"Pratapgarh".toLowerCase(),
    "RJ36" ->"Beawar".toLowerCase(),
    "RJ37" ->"Didwana".toLowerCase(),
    "RJ38" ->"Rawhhuu".toLowerCase()
  )

  val DIST_GUJARAT = Map("GJ01"->"Ahmedabad".toLowerCase(),
    "GJ02"->"Mehsana".toLowerCase(),
    "GJ03"->"Rajkot".toLowerCase(),
    "GJ04"->"Bhavnagar".toLowerCase(),
    "GJ05"->"Surat".toLowerCase(),
    "GJ06"->"Vadodara".toLowerCase(),
    "GJ07"->"Kheda".toLowerCase(),
    "GJ08"->"Banaskantha".toLowerCase(),
    "GJ09"->"Sabarkantha".toLowerCase(),
    "GJ10"->"Jamnagar".toLowerCase(),
    "GJ11"->"Junagadh".toLowerCase(),
    "GJ12"->"Kutch".toLowerCase(),
    "GJ13"->"Surendranagar".toLowerCase(),
    "GJ14"->"Amreli".toLowerCase(),
    "GJ15"->"Valsad".toLowerCase(),
    "GJ16"->"Bharuch".toLowerCase(),
    "GJ17"->"Panchmahal".toLowerCase(),
    "GJ18"->"Gandhinagar".toLowerCase(),
    "GJ19"->"Bardoli".toLowerCase(),
    "GJ20"->"Dahod".toLowerCase(),
    "GJ21"->"Navsari".toLowerCase(),
    "GJ22"->"Narmada".toLowerCase(),
    "GJ23"->"Anand".toLowerCase(),
    "GJ24"->"Patan".toLowerCase(),
    "GJ25"->"Porbander".toLowerCase(),
    "GJ26"->"Vyara".toLowerCase(),
    "GJ27"->"Ahmedabad East".toLowerCase(),
    "GJ30"->"Aahwa".toLowerCase(),
    "GJ31"->"Modasa".toLowerCase(),
    "GJ32"->"Veraval".toLowerCase(),
    "GJ33"->"Botad".toLowerCase(),
    "GJ34"->"Chhota Udepur".toLowerCase(),
    "GJ35"->"Lunavada".toLowerCase(),
    "GJ36"->"Morbi".toLowerCase(),
    "GJ37"->"Khambhaliya".toLowerCase(),
    "GJ38"->"Bavla".toLowerCase())

  /**
   *  state to dist map
   */

  val STATE_DIST: Map[String,Map[String,String]]= Map(STATE_RAJASTHAN->DIST_RAJASTHAN,STATE_GUJARAT->DIST_GUJARAT)

}
