# Context Parameters in Scala 3

---

## 1. Що таке Context Parameters?

**Context Parameters** (також відомі як `using`/`given`) — це нова синтаксична форма в Scala 3 для визначення **неявних залежностей**.

Вони дозволяють:
- **декларувати залежності явно, але передавати їх неявно**
- **замість явного передавання параметрів**, використовувати `given` для доступу до них

---

## 2. Яку проблему розв'язують Context Parameters?

До Scala 3 використовували `implicit` як:
- параметри для залежностей (DI)
- інстанси type classes (наприклад, `Ordering`, `Show`, `Eq`)
- контексти (ExecutionContext, Logger, конфігурація тощо)

Але `implicit` мав **неоднозначний синтаксис**:
- важко читати
- плутався з implicit conversion
- обмежений у читабельності та підтримці IDE

**Context Parameters (`using`/`given`) розвʼязують ці проблеми** через декларативний, типобезпечний і читаємий підхід.

---

## 3. Робочі приклади (Scala 3)

### 📌 Приклад 1: Простий context parameter

```scala
def greet(name: String)(using prefix: String): String =
  s"$prefix $name"

given String = "Hello"

@main def demo1() =
  println(greet("Serhii")) // Hello Serhii
```

---

### 📌 Приклад 2: Контекстна залежність через type class

```scala
trait Show[T]:
  def show(value: T): String

given Show[Int] with
  def show(value: Int): String = s"Int($value)"

def printValue[T](value: T)(using s: Show[T]): String =
  s.show(value)

@main def demo2() =
  println(printValue(42)) // Int(42)
```

---

### 📌 Приклад 3: Неявне підключення context параметра

```scala
case class Config(appName: String)

given Config = Config("MyApp")

def log(message: String)(using cfg: Config): Unit =
  println(s"[${cfg.appName}] $message")

@main def demo3() =
  log("Started") // [MyApp] Started
```

---

## 4. Тести (ScalaTest)

```scala
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ContextParametersTest extends AnyFlatSpec with Matchers {

  def greet(name: String)(using prefix: String): String =
    s"$prefix $name"

  given String = "Hi"

  trait Show[T]:
    def show(value: T): String

  given Show[Int] with
    def show(value: Int): String = s"Int($value)"

  def printValue[T](value: T)(using s: Show[T]): String =
    s.show(value)

  case class Config(appName: String)

  given Config = Config("TestApp")

  def log(msg: String)(using cfg: Config): String =
    s"[${cfg.appName}] $msg"

  "greet" should "prepend prefix" in {
    greet("World") shouldEqual "Hi World"
  }

  "printValue" should "use Show type class" in {
    printValue(10) shouldEqual "Int(10)"
  }

  "log" should "use config from context" in {
    log("running") shouldEqual "[TestApp] running"
  }
}
```

---

## 5. Питання та відповіді для співбесіди

### ❓ Що таке `using` параметри?

🔹 Це спосіб передавати параметри неявно, якщо вони є в області видимості як `given`.

---

### ❓ Що таке `given`?

🔹 `given` — це значення, яке може бути використано як контекстний параметр. Воно автоматично передається у функції з `using`.

---

### ❓ Чим `using`/`given` краще за `implicit`?

🔹 Вони читаються краще, підтримуються IDE, і розділені від implicit-conversion.

---

### ❓ Чи можна оголошувати кілька параметрів `using`?

🔹 Так, синтаксис: `(using a: A, b: B) => ...`

---

### ❓ Чи `using` працює з типами класів?

🔹 Так. Це основна техніка для type class-based програмування у Scala 3.

---

## 6. Переваги Context Parameters

- Читабельніші за implicit
- Працюють краще з IDE / тулінгом
- Підтримують типову класифікацію (type class style)
- Дають гнучкість для DI, логування, конфігурації, форматування тощо
