package _07_Functions.partial

enum OrderStatus:
  case Pending, Processing, Cancelled, Completed, Unknown

case class Order(id: Int, status: OrderStatus)
