resolvers ++= Seq(
  Classpaths.sbtPluginSnapshots,
  Classpaths.sbtPluginReleases,
  Resolver.sonatypeRepo("snapshots"),
  "Twitter Maven" at "https://maven.twttr.com"
)

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.13.0")
addSbtPlugin("io.get-coursier" % "sbt-coursier" % "1.0.0-RC5")
addSbtPlugin("com.twitter" % "scrooge-sbt-plugin" % "4.10.0")
