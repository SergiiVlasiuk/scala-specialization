package a_taste_of_scala3._05_Domain_Modeling.oopmodeling.domain

trait Person:
  def name: String

final case class Customer(name: String) extends Person:
  def placeOrder(drink: Drink): Order =
    Order(this, drink)

final case class Barista(name: String) extends Person:
  def prepareOrder(order: Order): PreparedDrink =
    PreparedDrink(order.customer, order.drink, self = this)
