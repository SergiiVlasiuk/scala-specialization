package _11_Types_and_the_Type_System

sealed trait Expr {
  type Out
  def eval: Out
}

case class IntExpr(value: Int) extends Expr {
  type Out = Int
  def eval: Int = value
}

case class BoolExpr(value: Boolean) extends Expr {
  type Out = Boolean
  def eval: Boolean = value
}

val evaluate: (e: Expr) => e.Out = _.eval
