package _12_Contextual_Abstractions

import _12_Contextual_Abstractions.Scala2Compat.*
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class Scala2CompatTest extends AnyFlatSpec with Matchers:

  given String = "Hello"

  "greet" should "work with using" in {
    greet("Scala 3") shouldEqual "Hello Scala 3"
  }

  "printValue" should "use given Show[Int]" in {
    printValue(42) shouldEqual "42"
  }

  import scala.language.implicitConversions

  given Conversion[Int, String] with
    def apply(x: Int): String = s"Number($x)"

  "Conversion" should "convert Int to String" in {
    val s: String = 10
    s shouldEqual "Number(10)"
  }
