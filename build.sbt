val versions = new {
  val paradise = "2.1.0"
}

val commonSettings = Seq(
  organization := "be.halvorg",
  version := "1.0.0",
  scalaVersion := "2.11.8",
  resolvers += Resolver.sonatypeRepo("snapshots"),
  resolvers += Resolver.sonatypeRepo("releases"),
  scalacOptions ++= Seq(
    "-deprecation",
    "-encoding", "UTF-8",
    "-feature",
    "-unchecked",
    "-Xlint",
    "-Yno-adapted-args",
    "-Ywarn-dead-code",
    "-Ywarn-numeric-widen",
    "-Ywarn-value-discard",
    "-Xfuture",
    "-Ywarn-unused-import"
  ),
  addCompilerPlugin("org.scalamacros" % "paradise" % versions.paradise cross CrossVersion.full)
)

lazy val yoink: Project = Project(
  "yoink",
  file("."),
  settings = commonSettings ++ Seq(
    run <<= run in Compile in core
  )
).aggregate(macros, core)

lazy val macros: Project = Project(
  "macros",
  file("macros"),
  settings = commonSettings ++ Seq(
    libraryDependencies <+= (scalaVersion)("org.scala-lang" % "scala-reflect" % _)
  )
)


lazy val core: Project = Project(
  "core",
  file("core"),
  settings = commonSettings
).dependsOn(macros)
