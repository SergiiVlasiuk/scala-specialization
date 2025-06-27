package _07_Functions.partial

import org.scalatest.funsuite.AnyFunSuite

class OrderProcessorSpec extends AnyFunSuite:

  test("only defined statuses are processed") {
    val orders = List(
      Order(1, OrderStatus.Pending),
      Order(2, OrderStatus.Completed),
      Order(3, OrderStatus.Processing),
      Order(4, OrderStatus.Cancelled),
      Order(5, OrderStatus.Unknown)
    )

    val processed = OrderProcessor.process(orders)

    assert(processed == List(
      "Order 1: Sending email to customer...",
      "Order 3: Attempting to ship...",
      "Order 4: Logging cancellation."
    ))
  }
