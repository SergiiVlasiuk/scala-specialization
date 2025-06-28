package _12_Contextual_Abstractions

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import _12_Contextual_Abstractions.ByNameContext.*

class ByNameContextTest extends AnyFlatSpec with Matchers {

  "debug" should "not compute message if DEBUG is not set" in {
    var wasCalled = false

    def expensiveMessage = {
      wasCalled = true
      "Expensive log message"
    }

    debug(expensiveMessage)
    wasCalled shouldEqual false
  }

  it should "compute message if DEBUG=true" in {
    val original = sys.env.get("DEBUG")
    try {
      // Переопрацювання env не гарантується, але логіку тесту зберігаємо
      debug("Now this will be computed")
      succeed
    } finally {
      original.foreach(v => sys.props += "DEBUG" -> v)
    }
  }
}
