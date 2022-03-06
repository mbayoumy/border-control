name := "border-control"

version := "0.1"

scalaVersion := "2.13.8"

idePackagePrefix := Some("uk.border.control")

val http4sVersion = "0.23.10"

libraryDependencies  ++= Seq(
  "org.http4s" %% "http4s-dsl" % http4sVersion,
  "org.http4s" %% "http4s-blaze-server" % http4sVersion,
  "org.http4s" %% "http4s-blaze-client" % http4sVersion,
  "org.http4s" %% "http4s-circe" % http4sVersion,
  "io.circe" %% "circe-literal" % "0.13.0",
  "com.github.fd4s" %% "fs2-kafka" % "3.0.0-M4",
  "io.circe" %% "circe-generic" % "0.13.0",
  "dev.profunktor" % "redis4cats-effects_2.13" % "1.0.0",
  "com.github.fd4s" %% "fs2-kafka" % "3.0.0-M4",
  "eu.timepit" %% "refined" % "0.9.28"
)