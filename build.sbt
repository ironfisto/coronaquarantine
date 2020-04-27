name := """covid"""
organization := "mighty devs"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.6"

scalacOptions += "-Ybreak-cycles"

libraryDependencies += guice
libraryDependencies += ws
libraryDependencies += ehcache

libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test
libraryDependencies ++= Seq(
  "com.michaelpollmeier" %% "gremlin-scala" % "3.3.3.17",
  "com.datastax.dse" % "dse-java-driver-graph" % "1.8.2",
  "net.codingwell" %% "scala-guice" % "4.1.0",
  "io.swagger" %% "swagger-play2" % "1.6.0"
)


libraryDependencies += "com.github.firebase4s" %% "firebase4s" % "0.0.4"
// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.orbigo.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.orbigo.binders._"

//solr to scala library
