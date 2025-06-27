# Kind Polymorphism у Scala 3
## Що таке Kind?
Kind — це "тип типів". Він описує, скільки параметрів типу приймає тип:

- `Int` має kind `*`
- `List` має kind `* -> *`

## Що таке Kind Polymorphism?
Це здатність писати код, який працює з типами різних kind, тобто узагальнення по kind.

---

## Приклад 1: Functor для типу `F[_]`

```scala
trait Functor[F[_]]:
  def map[A, B](fa: F[A])(f: A => B): F[B]

given Functor[List] with
  def map[A, B](fa: List[A])(f: A => B): List[B] = fa.map(f)

def transform[F[_]: Functor, A, B](fa: F[A])(f: A => B): F[B] =
  summon[Functor[F]].map(fa)(f)
```

---

## Тести

```scala
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class KindPolymorphismTest extends AnyFlatSpec with Matchers {

  trait Functor[F[_]]:
    def map[A, B](fa: F[A])(f: A => B): F[B]

  given Functor[List] with
    def map[A, B](fa: List[A])(f: A => B): List[B] = fa.map(f)

  def transform[F[_]: Functor, A, B](fa: F[A])(f: A => B): F[B] =
    summon[Functor[F]].map(fa)(f)

  "transform" should "map over List" in {
    transform(List(1, 2, 3))(_ * 2) shouldEqual List(2, 4, 6)
  }
}
```

---

## Питання для співбесіди
### Що таке Kind?
Kind — "тип типів", описує форму типу.

### Що таке Kind Polymorphism?
Можливість узагальнюватися по kind.

### Навіщо це потрібно?
Для гнучких, універсальних абстракцій.
