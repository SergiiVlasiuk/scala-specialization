package a_taste_of_scala3._05_Domain_Modeling.task.fp

import a_taste_of_scala3._05_Domain_Modeling.task.fp.CafeLogic.totalPerCustomer

@main def runCafeTotals(): Unit =
  val serhii = Customer("Serhii")
  val ivan = Customer("Ivan")

  val orders = List(
    Order(serhii, Drink(DrinkType.Latte, DrinkSize.Medium)),
    Order(serhii, Drink(DrinkType.Espresso, DrinkSize.Small)),
    Order(ivan, Drink(DrinkType.Cappuccino, DrinkSize.Large)),
    Order(ivan, Drink(DrinkType.Latte, DrinkSize.Small))
  )

  val totals = totalPerCustomer(orders)

  totals.foreach { case (customer, total) =>
    println(s"${customer.name} owes $$${total}")
  }
