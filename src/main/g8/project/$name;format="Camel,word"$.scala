import sbt._
import Keys._

object $name;format="Camel,word"$Build extends sbt.Build {
  
  lazy val core =
    Project(id = "$name;format="normalize"$-core",
            base = file("."),
            settings = Project.defaultSettings ++ Seq(
              organization := "$organization$",
              version := "$version$",
              scalaVersion := "2.9.1",
              crossScalaVersions := Seq("2.8.1, 2.9.0, 2.9.0-1"),
              initialCommands := "import $organization$.$name;format="normalize,word"$._",
              libraryDependencies <++= scalaVersion (v => Seq(
                Shared.specsDep(v) % "test"
            ))))
}

object Shared {
  
  /** Resolve specs version for the current scala version (thanks @n8han). */
  def specsDep(sv: String) =
    sv.split("[.-]").toList match {
      case "2" :: "8" :: _ => "org.scala-tools.testing" % "specs_2.8.1" % "1.6.8"
      case "2" :: "9" :: "1" :: _ => "org.scala-tools.testing" % "specs_2.9.1" % "1.6.9"
      case "2" :: "9" :: _ => "org.scala-tools.testing" %% "specs" % "1.6.8"
      case _ => sys.error("Specs not supported for scala version %s" format sv)
    }
  
}
