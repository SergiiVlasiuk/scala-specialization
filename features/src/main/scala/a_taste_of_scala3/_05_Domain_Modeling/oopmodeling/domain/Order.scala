package a_taste_of_scala3._05_Domain_Modeling.oopmodeling.domain

final case class Order(customer: Customer, drink: Drink)

final case class PreparedDrink(customer: Customer, drink: Drink, self: Barista):
  def serve(): Unit =
    println(s"${self.name} served ${drink.size} ${drink.drinkType} to ${customer.name}")
