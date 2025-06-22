package a_taste_of_scala3._05_Domain_Modeling.oopmodeling.logic

import a_taste_of_scala3._05_Domain_Modeling.oopmodeling.domain.*

@main def simulateCafe(): Unit =
  val customer = Customer("Serhii")
  val barista = Barista("Oksana")

  val order = customer.placeOrder(Drink(DrinkType.Latte, DrinkSize.Medium))
  val prepared = barista.prepareOrder(order)

  prepared.serve()
