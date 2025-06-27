package _08_Packaging_and_Imports

object ScopedImports:
  def loadConfig(path: String): Unit =
    if path.endsWith(".json") then
      import scala.util.chaining._
      val raw = """{ "x": 1 }"""
      println(raw.tap(r => println("Processing JSON")))
    else
      println("Unknown format")
