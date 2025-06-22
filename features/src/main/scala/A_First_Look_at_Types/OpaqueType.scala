package A_First_Look_at_Types

object Logarithm:
  opaque type Log = Double

  def apply(x: Double): Log =
    require(x > 0, "must be > 0")
    math.log(x)

  def toDouble(log: Log): Double = log
