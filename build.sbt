name := "rethinkdb-tester"

version := "0.1"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.4.1",
  "com.github.fomkin" %% "pushka-json" % "0.3.2",
  "com.github.fomkin" %% "scala-reql-akka" % "0.2.2",
  "com.github.fomkin" %% "scala-reql-pushka" % "0.2.2"
)

addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0-M5" cross CrossVersion.full)
