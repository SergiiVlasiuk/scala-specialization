package _05_Domain_Modeling

enum OrderStatus:
  case Placed, Paid, Shipped, Delivered

case class Order(id: Int, amount: Double, status: OrderStatus)

def pay(order: Order): Option[Order] =
  if order.status == OrderStatus.Placed then
    Some(order.copy(status = OrderStatus.Paid))
  else None
