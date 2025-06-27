package _07_Functions.partial

object OrderProcessor:

  val handler: PartialFunction[Order, String] = {
    case Order(id, OrderStatus.Pending) =>
      s"Order $id: Sending email to customer..."

    case Order(id, OrderStatus.Processing) =>
      s"Order $id: Attempting to ship..."

    case Order(id, OrderStatus.Cancelled) =>
      s"Order $id: Logging cancellation."
  }

  def process(orders: List[Order]): List[String] =
    orders.collect(handler)
