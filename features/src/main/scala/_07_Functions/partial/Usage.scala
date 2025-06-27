package _07_Functions.partial

@main def Usage() =
  val orders = List(
    Order(1, OrderStatus.Pending),
    Order(2, OrderStatus.Completed),
    Order(3, OrderStatus.Processing),
    Order(4, OrderStatus.Cancelled),
    Order(5, OrderStatus.Unknown)
  )

  val results = OrderProcessor.process(orders)
  results.foreach(println)

