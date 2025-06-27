package _01_a_taste_of_scala3.how_to_trait

import org.scalatest.funsuite.{AnyFunSuite}

import java.io.{ByteArrayOutputStream, PrintStream}

class MyClassTest extends AnyFunSuite {
  test("MyClass.hello prints correct messages") {
    val outStream = new ByteArrayOutputStream()
    Console.withOut(new PrintStream(outStream)) {
      val instance = new MyClass()
      instance.hello()
    }
    val output = outStream.toString
    assert(output.contains("MyClass:"))
    assert(output.contains("B"))
    assert(output.contains("A"))
  }

}
