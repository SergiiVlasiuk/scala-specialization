package _05_Domain_Modeling

object ShapeUtils:
  def area(shape: Shape): Double = shape match
    case Circle(r)       => math.Pi * r * r
    case Rectangle(w, h) => w * h
