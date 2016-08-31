import sbt._
import _root_.project.Version

object Lib {
  def compile(deps: ModuleID*): Seq[ModuleID] = deps map (_ % "compile")

  def provided(deps: ModuleID*): Seq[ModuleID] = deps map (_ % "provided")

  def test(deps: ModuleID*): Seq[ModuleID] = deps map (_ % "test")

  val repos = Seq(
    "Central Maven Repository" at "https://repo1.maven.org/maven2",
    "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases/",
    "Scalaz" at "https://dl.bintray.com/scalaz/releases",
    "JBoss" at "http://repository.jboss.org/nexus/content/groups/public/"
  )

  val mysqlconn = "mysql" % "mysql-connector-java" % Version.MYSQLCONN
  val slick = "com.typesafe.slick" %% "slick" % Version.SLICK
  val hibernate = "org.hibernate" % "hibernate-entitymanager" % Version.HIBERNATE
  val javaee = "javax" % "javaee-api" % Version.JAVAEE
  val command = "org.scala-sbt" % "command" % Version.SBT
  val cucumberJava = "info.cukes" % "cucumber-java" % Version.CUCUMBER
  val cucumberWeld = "info.cukes" % "cucumber-weld" % Version.CUCUMBER
  val cucumberJUnit = "info.cukes" % "cucumber-junit" % Version.CUCUMBER
  val junit = "junit" % "junit" % Version.JUNIT
  val weld = "org.jboss.weld.se" % "weld-se" % Version.WELD
  val cucumberGuice = "info.cukes" % "cucumber-guice" % Version.CUCUMBER
  val scalaGuice = "net.codingwell" % "scala-guice_2.11" % Version.SCALAGUICE
  val logback = "ch.qos.logback" % "logback-classic" % Version.LOGBACK
  val playws = "com.typesafe.play" % "play-ws_2.11" % Version.PLAYWS
  val jaxrsclient = "org.jboss.resteasy" % "resteasy-client" % Version.RESTEASY
  val jaxrsjsonpprovider = "org.jboss.resteasy" % "resteasy-json-p-provider" % Version.RESTEASY
  val jackson = "org.codehaus.jackson" % "jackson-mapper-asl" % Version.JACKSON
  val jacksonprovider = "org.jboss.resteasy" % "resteasy-jackson-provider" % Version.RESTEASY
  val jaxb = "javax.xml.bind" % "jaxb-api" % Version.JAXB
  val dbunit = "org.dbunit" % "dbunit" % Version.DBUNIT
}