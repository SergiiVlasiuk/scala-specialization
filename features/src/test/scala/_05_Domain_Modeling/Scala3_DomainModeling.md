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

## 🔸 Доповнення: Використання `opaque type` у Domain Modeling

`opaque type` у Scala 3 — це інструмент інкапсуляції, який дозволяє створювати типи, які ззовні виглядають як окремі, але не мають runtime-навантаження.

### 🔐 Мотивація:
Тип `Int` може використовуватися як:
- `UserId`
- `OrderId`
- `ProductId`

Але вони всі — просто `Int`, і можуть бути переплутані.

### ✅ Рішення:

```scala
object domain:
  opaque type OrderId = Int

  object OrderId:
    def apply(i: Int): OrderId = i
    def unsafe(value: Int): OrderId = value

  extension (id: OrderId)
    def value: Int = id
```

### 🔧 Використання:

```scala
import domain._

case class Order(id: OrderId, amount: Double)
```

Так НЕ спрацює:
```scala
val order = Order(123, 100.0) // 🔴 помилка компіляції
```

Так ПРАВИЛЬНО:
```scala
val order = Order(OrderId(123), 100.0)
```

### 🧪 Тест:

```scala
test("opaque type OrderId must wrap Int explicitly") {
  val id = OrderId(42)
  val order = Order(id, 150.0)
  assert(order.id.value == 42)
}
```

### ✅ Переваги:
- Повна типобезпека
- Неможливо переплутати `UserId` і `OrderId`
- Немає накладних витрат у runtime

