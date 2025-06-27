package _07_Functions

import org.scalatest.funsuite.AnyFunSuite

class EtaExpansionExampleTest extends AnyFunSuite:

  import EtaExpansionExample._

  test("multiply method can be assigned to a function") {
    val f: (Int, Int) => Int = multiply
    assert(f(3, 4) == 12)
  }

  test("greet method can be passed as a function") {
    val names = List("Alice", "Bob")
    val greetings = names.map(greet)
    assert(greetings == List("Hello, Alice", "Hello, Bob"))
  }

  test("noParams method is not eta-expanded automatically") {
    // val f: () => String = noParams // це не скомпілюється, бо noParams — без параметрів, треба явна лямбда

    val f: () => String = () => noParams()
    assert(f() == "No params here")
  }
