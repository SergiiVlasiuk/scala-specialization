abstract sealed class Expr
case class Number(n: Int) extends Expr
case class Sum(x: Number, y: Number) extends Expr
case class Prod(x: Number, y: Number) extends Expr
case class Var(name: String) extends Expr
case class Let(name: Var, rhs: Number, body: Var) extends Expr

def eval(e: Expr): Int =
  def recur(e: Expr)(using env: Map[String, Int]): Int = e match
    case Number(n)  => n
    case Sum(x, y)  => recur(x) + recur(y)
    case Prod(x, y) => recur(x) * recur(y)
    case Var(name)  => env(name)
//    case Let(name, rhs, body) => recur(body)(using env + (name -> recur(rhs)))
  recur(e)(using Map())


