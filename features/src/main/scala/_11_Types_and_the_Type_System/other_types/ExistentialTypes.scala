package _11_Types_and_the_Type_System.other_types

object ExistentialTypes:

  // A trait representing a generic container
  trait Container[T]:
    def get: T

  // Method accepting container of unknown T
  def printUnknown(container: Container[?]): String =
    val value = container.get
    s"Got value: $value"

  // Concrete containers
  class StringContainer extends Container[String]:
    def get: String = "Scala"

  class IntContainer extends Container[Int]:
    def get: Int = 42
