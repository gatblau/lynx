import Lib._
import sbt._

name := "lynx"

version := "1.0"

scalaVersion := "2.11.8"
conflictManager := ConflictManager.strict

lazy val lynx = (project in file("."))
  .aggregate(content, cli)

lazy val content = (project in file("content"))
  .settings(Settings.basicSettings: _*)
  .enablePlugins(PlayScala)
    .settings(PlayKeys.externalizeResources := false)
    .settings(mainClass in (assembly) := Some("play.core.server.ProdServerStart"))
    .settings(fullClasspath in assembly += Attributed.blank(PlayKeys.playPackageAssets.value))
    .settings(assemblyMergeStrategy in assembly := {
      case PathList("javax", "transaction", xs @ _*) => MergeStrategy.last
      case PathList("javax", "el", xs @ _*) => MergeStrategy.first
      case "META-INF/DEPENDENCIES.txt" => MergeStrategy.first
      case "META-INF/io.netty.versions.properties" => MergeStrategy.first
      case x =>
        val oldStrategy = (assemblyMergeStrategy in assembly).value
        oldStrategy(x)
    })
  .settings(libraryDependencies ++=
    Lib.compile(
      filters,
      hibernate,
      weld,
      mysqlconn,
      javaJdbc,
      javaJpa,
      playmailer,
      jacksonscalamodule
    )
  ).dependsOn(api)

lazy val cli = (project in file("cli"))
  .settings(Settings.basicSettings: _*)
  .settings(libraryDependencies ++=
    Lib.compile(
      command,
      weld,
      ws,
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
    ) ++
      Lib.test(
        cucumberJava,
        cucumberWeld,
        cucumberJUnit,
        junit
      )
  )
