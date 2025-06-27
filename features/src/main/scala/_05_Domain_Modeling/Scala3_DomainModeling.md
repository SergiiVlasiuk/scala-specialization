# Scala 3: Domain Modeling

## 1. Tools

| Засіб             | Призначення                                 |
|-------------------|---------------------------------------------|
| `case class`      | Представлення даних                         |
| `enum`            | Перелік можливих значень                    |
| `sealed trait`    | Закрите ієрархічне дерево типів             |
| `opaque type`     | Інкапсуляція типу без накладних витрат      |
| `given / using`   | Контекстні залежності                       |

---

## 2. OOP Modeling

```scala
sealed trait Shape
case class Circle(radius: Double) extends Shape
case class Rectangle(w: Double, h: Double) extends Shape

def area(shape: Shape): Double = shape match
  case Circle(r)       => math.Pi * r * r
  case Rectangle(w, h) => w * h
```

- `sealed trait` + `case class` створюють ADT
- Використовує pattern matching для поведінки

---

## 3. Functional Modeling

```scala
enum OrderStatus:
  case Placed, Paid, Shipped, Delivered

case class Order(id: Int, amount: Double, status: OrderStatus)

def pay(order: Order): Option[Order] =
  if order.status == OrderStatus.Placed then
    Some(order.copy(status = OrderStatus.Paid))
  else None
```

- Поведінка — чисті функції
- Немає мутацій, використовуються копії

---

### FP-алгебра:

```scala
trait PaymentService:
  def pay(order: Order): Either[String, Order]

object LivePaymentService extends PaymentService:
  def pay(order: Order): Either[String, Order] =
    if order.status == OrderStatus.Placed then
      Right(order.copy(status = OrderStatus.Paid))
    else Left("Invalid state")
```

---

## Тестування:

```scala
class OrderTest extends AnyFunSuite:

  test("pay should succeed for Placed order") {
    val order = Order(1, 100.0, OrderStatus.Placed)
    val paid = pay(order)
    assert(paid.contains(order.copy(status = OrderStatus.Paid)))
  }
```
