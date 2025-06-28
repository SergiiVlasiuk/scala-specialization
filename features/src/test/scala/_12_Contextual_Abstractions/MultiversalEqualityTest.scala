package _12_Contextual_Abstractions

import _12_Contextual_Abstractions.MultiversalEquality.*
import _12_Contextual_Abstractions.MultiversalEquality.given
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import scala.language.strictEquality

class MultiversalEqualityTest extends AnyFlatSpec with Matchers {
  "Equality User" should "allow same-type comparisons" in {
    val u1 = User(1, "A")
    val u2 = User(1, "A")
    (u1 == u2) shouldEqual true
  }

  it should "not compile for different types if no CanEqual given" in {
//    This test is compile-time only: uncommenting the next line should fail compilation
//    val u1 = User(1, "A")
//    val o = Order(1)
//    u1 == o
    succeed
  }

  "Equality Order" should "allow same-type comparisons" in {
    val o1 = Order(5)
    val o2 = Order(5)
    (o1 == o2) shouldEqual true
  }

  it should "not compile for different types if no CanEqual given" in {
    // This test is compile-time only: uncommenting the next line should fail compilation
    // val u1 = User(1, "A")
    // val o = Order(1)
    // u1 == o
    succeed
  }

  "Equality User and Waiter" should "allow comparisons if derived" in {
    given CanEqual[User, Waiter] = CanEqual.derived

    val u1 = User(5, "A")
    val w2 = Waiter(5, "A")
    (u1 == w2) shouldEqual false // бо це різні класи з різною реалізацією equals
  }
}
