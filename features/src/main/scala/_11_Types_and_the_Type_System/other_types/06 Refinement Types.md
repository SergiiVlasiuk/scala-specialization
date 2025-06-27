# Refinement Types у Scala 3

## Що таке Refinement Types?

Refinement Type — це тип, який **уточнює або розширює вже існуючий тип**, додаючи до нього нові члени: методи або поля.

```scala
Animal { def name: String }
```

---

## Навіщо потрібні Refinement Types?

- **Уточнити поведінку типу без створення нового**
- **Duck typing з типобезпекою**
- **Працювати з обʼєктами з додатковими властивостями**
- **Інтеграція з Java або сторонніми API**

---

## Приклади

### Приклад 1: Refinement з методом `name`

```scala
trait Animal:
  def speak(): String

def callSpeak(a: Animal { def name: String }): String =
  s"${a.name} says: ${a.speak()}"

val dog: Animal {def name: String} = new Animal:
  def speak() = "Woof!"
  val name = "Buddy"

@main def refinementExample1(): Unit =
  println(callSpeak(dog)) // Buddy says: Woof!
```

---

### Приклад 2: Refinement для зовнішнього API

```scala
trait ExternalService:
  def connect(): Unit

def useService(s: ExternalService { def timeout: Int }): String =
  s"Connecting with timeout ${s.timeout}"

val service: ExternalService {def timeout: Int} = new ExternalService:
  def connect(): Unit = println("Connected!")
  val timeout = 30
```

---

### Приклад 3: Duck typing

```scala
def printName(entity: { def name: String }): String =
  s"Name: ${entity.name}"

case class Person(name: String, age: Int)

@main def refinementExample3(): Unit =
  val p = Person("Alice", 25)
  println(printName(p)) // Name: Alice
```

---

## Тести

```scala
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class RefinementTypesTest extends AnyFlatSpec with Matchers {

  trait Animal:
    def speak(): String

  val dog: Animal {def name: String} = new Animal:
    def speak() = "Woof!"
    val name = "Buddy"

  def callSpeak(a: Animal { def name: String }): String =
    s"${a.name} says: ${a.speak()}"

  def useService(s: ExternalService { def timeout: Int }): String =
    s"Connecting with timeout ${s.timeout}"

  trait ExternalService:
    def connect(): Unit

  val service: ExternalService {def timeout: Int} = new ExternalService:
    def connect(): Unit = ()
    val timeout = 42

  def printName(x: { def name: String }): String = s"Name: ${x.name}"

  case class Person(name: String, age: Int)

  "callSpeak" should "use refinement with name field" in {
    callSpeak(dog) shouldEqual "Buddy says: Woof!"
  }

  "useService" should "refine ExternalService with timeout" in {
    useService(service) shouldEqual "Connecting with timeout 42"
  }

  "printName" should "support structural refinement" in {
    printName(Person("Olga", 30)) shouldEqual "Name: Olga"
  }
}
```

---

## Питання для співбесіди

### ❓ Що таке refinement type?

🔹 Тип, який уточнює вже існуючий тип, додаючи нові поля чи методи.

---

### ❓ Навіщо вони потрібні?

🔹 Щоб описувати додаткові властивості типу без зміни існуючої ієрархії.

---

### ❓ Як виглядає refinement?

```scala
Animal { def name: String }
```

---

### ❓ Чи refinement — це duck typing?

🔹 Подібно, але в Scala це типобезпечна форма, яка перевіряється під час компіляції.

---

### ❓ Які недоліки?

🔹 Повільніше через reflection, важче відлагоджувати, складність читання.

