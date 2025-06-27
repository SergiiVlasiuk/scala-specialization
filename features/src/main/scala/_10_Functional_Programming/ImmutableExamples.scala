// ----------------------------------------
// Scala 3 — Immutable Values Examples
// ----------------------------------------

package _10_Functional_Programming

object ImmutableExamples:

  // Immutable val — cannot be reassigned
  val pi: Double = 3.14159

  // Immutable collections (default)
  val numbers: List[Int] = List(1, 2, 3)
  val moreNumbers: List[Int] = numbers :+ 4  // new list created

  // Immutable case class
  case class Person(name: String, age: Int)
  val john = Person("John", 30)
  val olderJohn = john.copy(age = 31) // returns a new instance

  // Immutable nested object using copy
  case class Address(city: String)
  case class Employee(name: String, address: Address)
  val emp = Employee("Sara", Address("Kyiv"))
  val relocated = emp.copy(address = emp.address.copy(city = "Lviv"))

  // Demonstrating function with immutable logic
  def incrementAll(xs: List[Int]): List[Int] =
    xs.map(_ + 1)
