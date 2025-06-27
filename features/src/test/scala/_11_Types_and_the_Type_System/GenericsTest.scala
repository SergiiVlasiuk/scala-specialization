package _11_Types_and_the_Type_System

import org.scalatest.funsuite.AnyFunSuite

class GenericsTest extends AnyFunSuite:

  test("identity returns input"):
    assert(identity(123) == 123)
    assert(identity("abc") == "abc")

  test("Box stores and returns value"):
    val box = Box(42)
    assert(box.get == 42)

  test("upperBoundExample returns max"):
    assert(upperBoundExample("abc", "xyz") == "xyz")

  test("maxList works for Int list"):
    assert(maxList(List(1, 3, 2)) == 3)

  test("covariant box can be used as supertype"):
    val dogBox: CovariantBox[Dog] = new CovariantBox(new Dog())
    var animalBox: CovariantBox[Animal] = dogBox
    assert(animalBox.value.name == "dog")
    val catBox: CovariantBox[Cat] = new CovariantBox(new Cat)
    animalBox = catBox
    assert(animalBox.value.name == "cat")
