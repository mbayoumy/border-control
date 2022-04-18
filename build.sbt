name := "border-control"

version := "0.1"

scalaVersion := "2.13.8"

idePackagePrefix := Some("uk.border.control")

val http4sVersion = "0.23.10"

libraryDependencies ++= Seq(
  "org.http4s" %% "http4s-dsl" % http4sVersion,
  "org.http4s" %% "http4s-blaze-server" % http4sVersion,
  "org.http4s" %% "http4s-blaze-client" % http4sVersion,
  "org.http4s" %% "http4s-circe" % http4sVersion,
  "io.circe" %% "circe-literal" % "0.13.0",
  "io.circe" %% "circe-generic" % "0.13.0",
  "org.typelevel" %% "log4cats-slf4j" % "2.2.0",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
)