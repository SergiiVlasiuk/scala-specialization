package a_taste_of_scala3._05_Domain_Modeling.fpmodeling.domain


enum DrinkSize:
  case Small, Medium, Large

enum DrinkType:
  case Espresso, Cappuccino, Latte, Tea

final case class Drink(drinkType: DrinkType, size: DrinkSize)

final case class Customer(name: String)
final case class Barista(name: String)

final case class Order(customer: Customer, drink: Drink)
final case class PreparedDrink(order: Order, by: Barista)
