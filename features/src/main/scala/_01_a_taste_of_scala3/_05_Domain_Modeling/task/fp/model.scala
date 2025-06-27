package _01_a_taste_of_scala3._05_Domain_Modeling.task.fp

enum DrinkType:
  case Espresso, Cappuccino, Latte

enum DrinkSize:
  case Small, Medium, Large

final case class Drink(kind: DrinkType, size: DrinkSize)
final case class Customer(name: String)
final case class Order(customer: Customer, drink: Drink)