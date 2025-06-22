package a_taste_of_scala3._05_Domain_Modeling.task.fp

object CafeLogic:
  def price(drink: Drink): Double = (drink.kind, drink.size) match
    case (DrinkType.Espresso, DrinkSize.Small)  => 2.0
    case (DrinkType.Espresso, DrinkSize.Medium) => 2.5
    case (DrinkType.Espresso, DrinkSize.Large)  => 3.0
    case (DrinkType.Cappuccino, DrinkSize.Small)  => 3.0
    case (DrinkType.Cappuccino, DrinkSize.Medium) => 3.5
    case (DrinkType.Cappuccino, DrinkSize.Large)  => 4.0
    case (DrinkType.Latte, DrinkSize.Small)  => 3.0
    case (DrinkType.Latte, DrinkSize.Medium) => 3.5
    case (DrinkType.Latte, DrinkSize.Large)  => 4.5

  def groupOrdersByCustomer(orders: List[Order]): Map[Customer, List[Order]] =
    orders.groupBy(_.customer)

  def totalPerCustomer(orders: List[Order]): Map[Customer, Double] =
    groupOrdersByCustomer(orders)
      .view
      .mapValues(_.map(o => price(o.drink)).sum)
      .toMap
