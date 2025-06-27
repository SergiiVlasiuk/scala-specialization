package _01_a_taste_of_scala3._05_Domain_Modeling.oopmodeling.domain

import _01_a_taste_of_scala3._05_Domain_Modeling.oopmodeling.domain.*
import org.scalatest.funsuite.AnyFunSuite

class CustomerSpec extends AnyFunSuite {

  test("Customer should have a name") {
    val customer = Customer("Serhii")
    assert(customer.name == "Serhii")
  }

  test("Customer should create a valid order") {
    val customer = Customer("Serhii")
    val drink = Drink(DrinkType.Espresso, DrinkSize.Small)
    val order = customer.placeOrder(drink)

    assert(order.customer == customer)
    assert(order.drink == drink)
  }
}
