package _01_a_taste_of_scala3._05_Domain_Modeling.oopmodeling.domain

enum DrinkSize:
  case Small, Medium, Large

enum DrinkType:
  case Espresso, Cappuccino, Latte, Tea

final case class Drink(drinkType: DrinkType, size: DrinkSize)
