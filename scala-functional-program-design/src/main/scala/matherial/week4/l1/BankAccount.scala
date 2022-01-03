package matherial.week4.l1

class BankAccount:
  private var balance = 0

  def deposit(amount: Int): Unit =
    if (amount > 0) then balance += amount

  def withdraw(amount: Int): Unit =
    if (0 < amount && amount <= balance) then balance -= amount
    else throw new Error("insufficient funds")

object Enter:
  def main(args: Array[String]): Unit =
    val account = BankAccount()
    account.deposit(50)
    account.withdraw(20)
    account.withdraw(20)
    account.withdraw(20)

