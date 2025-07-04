# Узагальнена схема використання "Other Types" у Scala

| Тип                         | Сфера застосування            | Вплив на сигнатуру | Вплив на тіло функції | Приклад використання |
|----------------------------|-------------------------------|---------------------|------------------------|-----------------------|
| **Type Lambdas**           | Типи-функції без імені        | ✅                  | ✅ опосередковано       | `type F[A] = Either[String, A]` |
| **Match Types**            | Обчислення типу за патерном   | ✅                  | ✅                     | `type Elem[X] = X match { ... }` |
| **Existential Types**      | "Існує такий тип T..."        | ✅                  | ❌                     | `List[_]` або `T forSome { ... }` |
| **Higher-Kinded Types**    | Типи з типами-параметрами     | ✅                  | ✅                     | `trait Functor[F[_]]` |
| **Singleton Types**        | Тип значення                  | ✅                  | ❌                     | `val x: "hello".type = "hello"` |
| **Refinement Types**       | Розширення типів методами     | ✅                  | ✅                     | `{ def close(): Unit }` |
| **Kind Polymorphism**      | Параметризація над "формою"   | ✅                  | ✅                     | `trait PolyKind[F[_[_]]]` |

---

## Тлумачення

- **Сигнатура** — ці типи впливають на те, як виглядає тип функції, класу або методу.
- **Тіло функції** — деякі з типів прямо або опосередковано визначають типи локальних змінних, умовну логіку або підстановки.

---

## Наступний крок

До кожного типу буде створено:
1. окремий `.scala` файл з прикладом;
2. окремий `.scala` файл з відповідним юніт-тестом (`ScalaTest`).

Ці файли будуть у форматі:

```
/types/
  TypeLambdas.scala
  MatchTypes.scala
  ...
/tests/
  TypeLambdasSpec.scala
  MatchTypesSpec.scala
  ...
```

✅ Далі згенерую ці файли.