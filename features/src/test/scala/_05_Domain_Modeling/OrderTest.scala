package _05_Domain_Modeling

import org.scalatest.funsuite.AnyFunSuite

class OrderTest extends AnyFunSuite:

  test("pay should succeed for Placed order") {
    val order = Order(1, 100.0, OrderStatus.Placed)
    val paid = pay(order)
    assert(paid.contains(order.copy(status = OrderStatus.Paid)))
  }
