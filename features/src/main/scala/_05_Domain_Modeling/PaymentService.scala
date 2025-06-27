package _05_Domain_Modeling

trait PaymentService:
  def pay(order: Order): Either[String, Order]

object LivePaymentService extends PaymentService:
  def pay(order: Order): Either[String, Order] =
    if order.status == OrderStatus.Placed then
      Right(order.copy(status = OrderStatus.Paid))
    else Left("Invalid state")
