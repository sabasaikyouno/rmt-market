name := """rmt-market"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.13"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "7.0.0" % Test
libraryDependencies += "org.typelevel" %% "cats-core" % "2.10.0"
libraryDependencies += "org.typelevel" %% "cats-effect" % "3.5.4"
libraryDependencies += "com.microsoft.playwright" % "playwright" % "1.42.0"
libraryDependencies += "commons-lang" % "commons-lang" % "2.6"
libraryDependencies += jdbc
libraryDependencies += evolutions
libraryDependencies += "org.scalikejdbc" %% "scalikejdbc" % "4.2.1"
libraryDependencies += "org.scalikejdbc" %% "scalikejdbc-config" % "4.2.1"
libraryDependencies += "org.scalikejdbc" %% "scalikejdbc-play-dbapi-adapter" % "3.0.0-scalikejdbc-4.2"
libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.33"
dependencyOverrides += "org.scala-lang.modules" %% "scala-parser-combinators" % "2.3.0"

val circeVersion = "0.14.1"

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser"
).map(_ % circeVersion)

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.example.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.example.binders._"
