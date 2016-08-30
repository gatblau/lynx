import Lib._
import sbt._

name := "lynx"

version := "1.0"

scalaVersion := "2.11.8"

lazy val lynx = (project in file("."))
  .aggregate(collect, ingest, cli)

lazy val collect = (project in file("collect"))
  .settings(Settings.basicSettings: _*)
  .enablePlugins(PlayScala)
  .settings(libraryDependencies ++=
    Lib.compile(
      javaee,
      hibernate,
      weld
    )
  ).dependsOn(api)

lazy val ingest = (project in file("ingest"))

lazy val cli = (project in file("cli"))
  .settings(Settings.basicSettings: _*)
  .settings(libraryDependencies ++=
    Lib.compile(
      command,
      javaee,
      weld,
      jaxrsclient,
      jaxrsjsonpprovider
    ) ++
      Lib.test(
        cucumberJava,
        cucumberWeld,
        cucumberJUnit,
        junit
      )
  ).dependsOn(api)

lazy val api = (project in file("api"))
  .settings(Settings.basicSettings: _*)
  .settings(libraryDependencies ++=
    Lib.compile(
      weld,
      logback
    )
  )