package exceptions

//TODO: Refined this exceptions later on
case class apiExceptions(message: String = "An Error Occurred",
                         cause: Throwable = None.orNull)
  extends Exception(message, cause)