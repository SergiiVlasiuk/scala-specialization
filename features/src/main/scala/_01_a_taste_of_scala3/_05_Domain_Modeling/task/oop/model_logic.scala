package _01_a_taste_of_scala3._05_Domain_Modeling.task.oop

enum DrinkSize:
  case Small, Medium, Large

enum DrinkType:
  case Espresso, Cappuccino, Latte

final case class Drink(kind: DrinkType, size: DrinkSize):
  def price: Double = (kind, size) match
    case (DrinkType.Espresso, DrinkSize.Small)  => 2.0
    case (DrinkType.Espresso, DrinkSize.Medium) => 2.5
    case (DrinkType.Espresso, DrinkSize.Large)  => 3.0
    case (DrinkType.Cappuccino, DrinkSize.Small)  => 3.0
    case (DrinkType.Cappuccino, DrinkSize.Medium) => 3.5
    case (DrinkType.Cappuccino, DrinkSize.Large)  => 4.0
    case (DrinkType.Latte, DrinkSize.Small)  => 3.0
    case (DrinkType.Latte, DrinkSize.Medium) => 3.5
    case (DrinkType.Latte, DrinkSize.Large)  => 4.5

final case class Order(drink: Drink)

final class Customer(val name: String):
  private var orders: List[Order] = Nil

  def placeOrder(drink: Drink): Unit =
    orders = orders :+ Order(drink)

  def total: Double = orders.map(_.drink.price).sum

  def report(): String = s"$name owes $$${total}"
