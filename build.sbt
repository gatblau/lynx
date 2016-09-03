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
    .settings(PlayKeys.externalizeResources := false)
//  .settings(mainClass in (assembly) := Some("play.core.server.ProdServerStart"))
//  .settings(fullClasspath in assembly += Attributed.blank(PlayKeys.playPackageAssets.value))
  .settings(libraryDependencies ++=
    Lib.compile(
      javaee,
      hibernate,
      weld,
      mysqlconn,
      javaJdbc,
      javaJpa,
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
      jacksonprovider
    ) ++
      Lib.test(
        cucumberJava,
        cucumberWeld,
        cucumberJUnit,
        junit,
        dbunit,
        mysqlconn
      )
  ).dependsOn(api)

lazy val api = (project in file("api"))
  .settings(Settings.basicSettings: _*)
  .settings(libraryDependencies ++=
    Lib.compile(
      weld,
      logback,
      jackson
    )
  )