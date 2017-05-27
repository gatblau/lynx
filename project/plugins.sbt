logLevel := Level.Warn

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.5.9")

addSbtPlugin("com.timushev.sbt" % "sbt-updates" % "0.2.0") // dependencyUpdates

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.2") // uber jar

addSbtPlugin("org.scala-sbt" % "sbt-duplicates-finder" % "0.7.0") // checkDuplicates

addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.8.2") // dependencyTree