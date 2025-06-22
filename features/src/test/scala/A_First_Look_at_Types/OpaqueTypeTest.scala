package A_First_Look_at_Types

import org.scalatest.funsuite.AnyFunSuite

class OpaqueTypeTest extends AnyFunSuite:

  test("Logarithm opaque type") {
    import Logarithm.*
    val logValue: Log = apply(10.0)
    val raw: Double = toDouble(logValue)
    assert(raw > 2.0 && raw < 3.0)
  }
