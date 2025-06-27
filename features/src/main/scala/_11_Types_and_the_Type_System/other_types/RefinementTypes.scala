package _11_Types_and_the_Type_System.other_types

import scala.reflect.Selectable.reflectiveSelectable
//import scala.language.reflectiveCalls

object RefinementTypes:
  trait Animal:
    def speak(): String

  val dog: Animal {def name: String} = new Animal:
    def speak() = "Woof!"

    val name = "Buddy"

  def callSpeak(a: Animal {def name: String}): String =
    s"${a.name} says: ${a.speak()}"

  def useService(s: ExternalService {def timeout: Int}): String =
    s"Connecting with timeout ${s.timeout}"

  trait ExternalService:
    def connect(): Unit

  val service: ExternalService {def timeout: Int} = new ExternalService:
    def connect(): Unit = ()

    val timeout = 42

  def printName(x: {def name: String}): String = s"Name: ${x.name}"

  case class Person(name: String, age: Int)
