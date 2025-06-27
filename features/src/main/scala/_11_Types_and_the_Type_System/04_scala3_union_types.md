# Union Types в Scala 3

## Що таке Union Type?

**Union Type** (`A | B`) означає, що значення може бути або типу `A`, або типу `B`. Це зручно для функцій, які приймають кілька типів аргументів або повертають значення різних типів залежно від логіки.

---

## Приклад 1: Обробка двох типів

```scala
def handle(value: String | Int): String =
  value match
    case s: String => s"String of length ${s.length}"
    case i: Int    => s"Integer squared is ${i * i}"

assert(handle("Scala") == "String of length 5")
assert(handle(4) == "Integer squared is 16")
```

---

## Приклад 2: Об'єднання типів у класі

```scala
case class Response(data: String | List[String])

def printData(resp: Response): Unit =
  resp.data match
    case s: String      => println(s"Single: $s")
    case l: List[_]     => println(s"List: ${l.mkString(", ")}")
```

---

## Inference of Union Types (виведення типу)

Якщо результат може бути кількох типів, компілятор Scala 3 автоматично виведе union type:

```scala
def parse(value: String): Int | Boolean =
  if value.matches("\\d+") then value.toInt
  else if value == "true" || value == "false" then value.toBoolean
  else throw new Exception("invalid")

val result = parse("123") // тип: Int | Boolean
```

---

## Alternative to Union Types у Scala 2

У Scala 2 Union Types не існували. Їх моделювали:

1. Через `Either[A, B]`
2. Через `sealed trait` + `case class`/`case object`

```scala
sealed trait Input
case class Text(value: String) extends Input
case class Number(value: Int) extends Input

def handle(i: Input): String = i match
  case Text(s)   => s"Text: $s"
  case Number(n) => s"Number: $n"
```

Union Types у Scala 3 роблять це значно простішим.

---

## Тести

```scala
test("handle union type values"):
  assert(handle("Scala") == "String of length 5")
  assert(handle(10) == "Integer squared is 100")

test("parse infers union result"):
  val res: Int | Boolean = parse("true")
  assert(res == true)
```

---

## Потенційні питання для співбесіди

**Q1: Що таке Union Type?**\
A1: Тип, що дозволяє значенням бути або одного типу, або іншого (наприклад, `String | Int`).

**Q2: У чому різниця між Union та Intersection типами?**\
A2: Union (`A | B`) — одне з двох, Intersection (`A & B`) — і те, і те одночасно.

**Q3: Які альтернативи Union Types у Scala 2?**\
A3: `Either`, `sealed trait` + `case class`, іноді `Any` (але не типобезпечно).

**Q4: Як працює виведення типу для Union?**\
A4: Якщо в тілі функції можливі різні типи результату, компілятор створює `A | B`.

---

## Короткий висновок

Union Types в Scala 3 — це потужний засіб для роботи з даними, що можуть бути кількох типів:

- ✅ Просте й зрозуміле API для споживачів
- ✅ Менше шаблонного коду (менше `Either`, `sealed trait`)
- ✅ Покращене виведення типів

> З Union Types код стає коротшим і яснішим, але не забувайте про `match` — без нього ви не зможете дістатися до значення конкретного типу.

