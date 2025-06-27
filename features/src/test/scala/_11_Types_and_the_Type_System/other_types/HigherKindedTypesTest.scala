package _11_Types_and_the_Type_System.other_types

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import _11_Types_and_the_Type_System.other_types.HigherKindedTypes.*

class HigherKindedTypesTest extends AnyFlatSpec with Matchers {

  "transform" should "map over a List" in {
    val result = transform(List(1, 2, 3))(_ * 2)
    result shouldEqual List(2, 4, 6)
  }

  it should "map over an Option" in {
    val result = transform[Option, Int, Int](Some(10))(_ + 5)
    result shouldEqual Some(15)
  }

  it should "handle None correctly" in {
    val result = transform(None: Option[Int])(_ + 5)
    result shouldEqual None
  }
}
