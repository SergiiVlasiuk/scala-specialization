//name := "Functional-Programming-in-Scala-Specialization"

//ThisBuild / scalaVersion := "3.1.0"
ThisBuild / scalaVersion := "3.7.1"
//ThisBuild / organization := "com.example"
//version := "0.1"
//scalaVersion := "3.1.0"
val scalaTestVersion = "3.2.16" // остання стабільна ScalaTest
val scalaTestLibrary = "org.scalatest" %% "scalatest" % scalaTestVersion % Test
val scalaCheck = "org.scalacheck" %% "scalacheck" % "1.17.0"
val scalaMeta = "org.scalameta" %% "munit" % "0.7.26" % Test

lazy val root = (project in file("."))
  .aggregate(`scala-functional-program-design`, `different-tasks`)
  .settings(
    name := "Functional-Programming-in-Scala-Specialization",
    libraryDependencies += scalaTestLibrary,
  )
  .dependsOn(`scala-functional-program-design`, `different-tasks`, `features`)

lazy val `scala-functional-program-design` = (project in file("./scala-functional-program-design"))
  .settings(libraryDependencies ++= {
    Seq(scalaTestLibrary, scalaCheck, scalaMeta)
  })

lazy val `different-tasks` = (project in file("./different-tasks"))
  .settings(libraryDependencies ++={
    Seq(scalaTestLibrary)
  })

lazy val `features` = (project in file("./features"))
  .settings(libraryDependencies ++={
    Seq(scalaTestLibrary)
  })
