# Scala 3: A First Look at Types — Part 5: Opaque, Inferred, Literal Types

## 1. Opaque Types

Opaque (непрозорі) типи дозволяють створювати нові типи, несумісні з базовими, але без runtime overhead.

```scala
object Logarithm:
  opaque type Log = Double

  def apply(x: Double): Log =
    require(x > 0)
    math.log(x)

  def toDouble(log: Log): Double = log
```

- `Log` — новий тип, видимий лише ззовні як абстракція
- Всередині `Logarithm` — це звичайний `Double`

## 2. Inferred Types

Scala виводить типи автоматично:

```scala
val x = 42           // Int
val name = "Scala"   // String
val nums = List(1,2) // List[Int]
```

> Але для API варто писати типи явно.

## 3. Literal Types

Типи можуть бути буквальними значеннями:

```scala
val On: true = true
val mode: "safe" = "safe"

def connect(mode: "safe" | "fast"): String =
  s"Connecting in $mode mode"
```

- Це корисно в DSL, конфігураціях і перевірках на етапі компіляції.
