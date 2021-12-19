//name := "Functional-Programming-in-Scala-Specialization"

ThisBuild / scalaVersion := "3.1.0"
//ThisBuild / organization := "com.example"
//version := "0.1"
//scalaVersion := "3.1.0"
val scalaTestLibrary = "org.scalatest" % "scalatest_2.12" % "3.1.0"

lazy val root = (project in file("."))
  .aggregate(`scala-functional-program-design`)
  .settings(
    name := "Functional-Programming-in-Scala-Specialization",
    libraryDependencies += scalaTestLibrary,
  )

lazy val `scala-functional-program-design` = (project in file("./scala-functional-program-design"))
  .settings(libraryDependencies ++= {
    Seq(scalaTestLibrary)
  })
