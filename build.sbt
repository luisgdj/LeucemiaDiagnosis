name := """LeucemiaDiagnosis"""
organization := "com.luisgdj"

version := "1.0"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

//scalaVersion := "2.12.2"
scalaVersion := "2.13.10"

libraryDependencies += guice
libraryDependencies ++=Seq("com.typesafe.play" %% "play" % "2.8.18")

// Drools
libraryDependencies ++= Seq(
  "org.drools" % "drools-core" % "7.65.0.Final",
  "org.drools" % "drools-compiler" % "7.65.0.Final",
//  "org.drools" % "drools-xml-support" % "7.65.0.Final",
  "org.drools" % "drools-mvel" % "7.65.0.Final")
