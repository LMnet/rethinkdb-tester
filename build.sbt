name := "rethinkdb-tester"

version := "0.1"

scalaVersion := "2.11.7"

val driverVersion = "0.2.10"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.4.1",
  "com.github.fomkin" %% "pushka-json" % "0.4.1",
  "com.github.fomkin" %% "scala-reql-akka" % driverVersion,
  "com.github.fomkin" %% "scala-reql-pushka" % driverVersion
)

addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)
