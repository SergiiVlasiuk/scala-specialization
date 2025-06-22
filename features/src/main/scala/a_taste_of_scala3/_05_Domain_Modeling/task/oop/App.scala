package a_taste_of_scala3._05_Domain_Modeling.task.oop

@main def runCafeOOP(): Unit =
  val serhii = Customer("Serhii")
  val ivan = Customer("Ivan")

  serhii.placeOrder(Drink(DrinkType.Latte, DrinkSize.Medium))
  serhii.placeOrder(Drink(DrinkType.Espresso, DrinkSize.Small))

  ivan.placeOrder(Drink(DrinkType.Cappuccino, DrinkSize.Large))
  ivan.placeOrder(Drink(DrinkType.Latte, DrinkSize.Small))

  println(serhii.report())
  println(ivan.report())