# Higher-Kinded Types (HKTs) у Scala 3

## 1. Що таке Higher-Kinded Types?

Higher-Kinded Types — це типи, які приймають як параметри інші типи. Наприклад, `F[_]` означає тип-конструктор, який приймає один тип, наприклад, `List`, `Option`.

## 2. Проблема, яку вирішують HKTs

Можливість писати універсальний код для роботи з контейнерами (списки, опції тощо), які підтримують операції, як-от `map`, без дублювання коду для кожного типу.

## 3. Приклад коду

```scala
trait Functor[F[_]]:
  def map[A, B](fa: F[A])(f: A => B): F[B]

given Functor[List] with
  def map[A, B](fa: List[A])(f: A => B): List[B] = fa.map(f)

given Functor[Option] with
  def map[A, B](fa: Option[A])(f: A => B): Option[B] = fa.map(f)

def transform[F[_]: Functor, A, B](fa: F[A])(f: A => B): F[B] =
  summon[Functor[F]].map(fa)(f)

@main def runExample() =
  println(transform(List(1, 2, 3))(_ * 2))   
  println(transform(Option(10))(_ + 5))      
  println(transform(None: Option[Int])(_ + 5)) 
```

## 4. Тести (ScalaTest)

```scala
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class HigherKindedTypesTest extends AnyFlatSpec with Matchers {

  "transform" should "map over a List" in {
    val result = transform(List(1, 2, 3))(_ * 2)
    result shouldEqual List(2, 4, 6)
  }

  it should "map over an Option" in {
    val result = transform[Option, Int, Int](Some(10))(_ + 5)
    result shouldEqual Some(15)
  }

  it should "handle None correctly" in {
    val result = transform[Option, Int, Int](None)(_ + 5)
    result shouldEqual None
  }
}
```

## 5. Питання та відповіді для співбесіди

### Питання 1: Що таке higher-kinded types?

**Відповідь:** Типи, які приймають інші типи як параметри. Наприклад, `F[_]` — це тип-конструктор, який очікує тип-параметр.

---

### Питання 2: Навіщо вони потрібні?

**Відповідь:** Щоб писати загальні абстракції для типів-контейнерів (наприклад `List`, `Option`, `Future`) без дублювання коду.

---

### Питання 3: Що таке type class?

**Відповідь:** Абстракція поведінки для типів, яка реалізується через окремі екземпляри (`given`) і дозволяє писати узагальнений код.

---

### Питання 4: Що таке `given`?

**Відповідь:** Спосіб у Scala 3 оголосити автоматично доступний екземпляр типу (імпліцитний параметр), який може бути використаний у функціях з `using` або `[T: TypeClass]`.

---

### Питання 5: Для чого `summon`?

**Відповідь:** Щоб явно отримати `given`-екземпляр типу з контексту, якщо він є. Альтернатива `implicitly` у Scala 2.

---

### Питання 6: Як викликати `transform` для `Option`?

**Відповідь:**

```scala
transform[Option, Int, Int](Some(10))(_ + 5)
```

або

```scala
transform(Some(10): Option[Int])(_ + 5)
```

щоб компілятор знав, що `F = Option`.

