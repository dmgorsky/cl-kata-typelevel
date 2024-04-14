
val toolkitV = "0.1.3"
val toolkit = "org.typelevel" %% "toolkit" % toolkitV
val toolkitTest = "org.typelevel" %% "toolkit-test" % toolkitV

ThisBuild / scalaVersion := "3.3.0"
libraryDependencies += toolkit
libraryDependencies += (toolkitTest % Test)
