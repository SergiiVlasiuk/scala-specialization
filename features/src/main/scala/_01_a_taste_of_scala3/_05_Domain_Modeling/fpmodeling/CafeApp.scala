package _01_a_taste_of_scala3._05_Domain_Modeling.fpmodeling

import _01_a_taste_of_scala3._05_Domain_Modeling.fpmodeling.domain.*
import _01_a_taste_of_scala3._05_Domain_Modeling.fpmodeling.domain.CafeLogic.*

@main def simulateCafe(): Unit =
  val customer = Customer("Serhii")
  val barista = Barista("Oksana")
  val drink = Drink(DrinkType.Latte, DrinkSize.Medium)

  val order = placeOrder(customer, drink)
  val prepared = prepareDrink(order, barista)
  val output = serveDrink(prepared)

  println(output)