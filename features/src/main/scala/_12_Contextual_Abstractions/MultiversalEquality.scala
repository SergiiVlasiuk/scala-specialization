package _12_Contextual_Abstractions

object MultiversalEquality:
  case class User(id: Int, name: String) derives CanEqual
  case class Order(id: Int)
  case class Waiter(id: Int, name: String)

  //given CanEqual[User, User] = CanEqual.derived
  given CanEqual[Order, Order] = CanEqual.derived
