package _11_Types_and_the_Type_System

import scala.reflect.Selectable.reflectiveSelectable

def closeQuietly(resource: { def close(): Unit }): Unit =
  try {
    resource.close()
  } catch {
    case _: Exception =>
  }

class File:
  def close(): Unit = println("File closed")

class Connection:
  def close(): Unit = println("Connection closed")

def printName(entity: { def name: String }): Unit = {
  println(entity.name)
}

val person = new {
  val name = "Serhii"
  val age = 30
}

val company = new {
  val name = "INTA"
  val location = "Kyiv"
}

def invokeClose(resource: Selectable { def close(): Unit }): Unit =
  try resource.close()
  catch { case _: Exception => () }
