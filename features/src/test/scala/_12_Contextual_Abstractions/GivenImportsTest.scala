package _12_Contextual_Abstractions

package givenimportsdemo

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import _12_Contextual_Abstractions.Instances.given  // Імпортуємо всі given для тестів

class GivenImportsTest extends AnyFlatSpec with Matchers {

  def printShow[T](value: T)(using s: Instances.Show[T]): String =
    s.show(value)

  "printShow" should "show Int correctly" in {
    printShow(42) shouldEqual "Int(42)"
  }

  it should "show String correctly" in {
    printShow("abc") shouldEqual "String(abc)"
  }
}
