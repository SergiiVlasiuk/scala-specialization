package a_taste_of_scala3._05_Domain_Modeling.oopmodeling.domain

import a_taste_of_scala3._05_Domain_Modeling.oopmodeling.domain.*
import org.scalatest.funsuite.AnyFunSuite

class BaristaSpec extends AnyFunSuite {

  test("Barista should have a name") {
    val barista = Barista("Oksana")
    assert(barista.name == "Oksana")
  }

  test("Barista should prepare a drink for a customer") {
    val customer = Customer("Serhii")
    val barista = Barista("Oksana")
    val drink = Drink(DrinkType.Latte, DrinkSize.Medium)
    val order = Order(customer, drink)

    val prepared = barista.prepareOrder(order)

    assert(prepared.customer == customer)
    assert(prepared.drink == drink)
    assert(prepared.self == barista)
  }
}
