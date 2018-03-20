name := """play-scala-starter-haoming"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

resolvers += Resolver.sonatypeRepo("snapshots")

scalaVersion := "2.12.4"

crossScalaVersions := Seq("2.11.12", "2.12.4")

libraryDependencies += guice

// Test dependencies
libraryDependencies += "com.typesafe.akka" %% "akka-testkit" % "2.5.2" % Test
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % Test
libraryDependencies += "org.awaitility" % "awaitility" % "3.0.0" % Test
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test

//DB
libraryDependencies += "com.h2database" % "h2" % "1.4.196"
libraryDependencies +=  "mysql" % "mysql-connector-java" % "6.0.6"
// Dependencies needed for Slick
libraryDependencies +="com.typesafe.play" %% "play-slick" % "3.0.1"
libraryDependencies +="com.typesafe.play" %% "play-slick-evolutions" % "3.0.1"
libraryDependencies += "com.typesafe.slick" %% "slick-codegen" % "3.2.0"
// Our streaming library
libraryDependencies += "io.monix" %% "monix" % "2.3.3"
libraryDependencies += "io.monix" %% "monix-cats" % "2.3.3"

