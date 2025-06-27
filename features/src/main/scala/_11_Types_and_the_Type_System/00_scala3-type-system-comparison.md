# Scala 3 Type System Comparison

## Overview

This document provides a comparative table and explanation for major type system features in Scala 3, including:

- Inferred Types
- Generics
- Intersection Types
- Union Types
- Algebraic Data Types
- Variance
- Opaque Types
- Structural Types
- Dependent Function Types
- Type Lambdas
- Match Types
- Existential Types
- Higher-Kinded Types
- Singleton Types
- Refinement Types
- Kind Polymorphism

---

## Higher-Kinded Types vs Kind Polymorphism

| Feature                | Higher-Kinded Types                                 | Kind Polymorphism                                                   |
|------------------------|-----------------------------------------------------|---------------------------------------------------------------------|
| **Definition**         | Types that take types as parameters                 | Abstractions over kinds (types of type constructors)               |
| **Example**            | `F[_]`, `F[_, _]`                                   | `F[_[_]]`, `F[G[_]]`, etc.                                          |
| **Use Case**           | Abstract over containers (e.g. `Functor[F[_]]`)     | Abstract over abstractions (e.g. `FunctorK[F[_[_]]]`)              |
| **Kind level**         | Works with specific kinds like `* -> *`             | Generalizes over arbitrary kinds                                   |
| **Power**              | Enables powerful abstractions for generic types     | Enables even more abstract and flexible type definitions            |
| **Limitation**         | Must specify kind in advance                        | Kind is parameterized                                              |

---

## General Type Feature Comparison

| Category                | Example                     | Kind           | Description                                                                 |
|-------------------------|-----------------------------|----------------|-----------------------------------------------------------------------------|
| **Inferred Types**      | `val x = 42`                | `*`            | Types inferred by compiler                                                 |
| **Generics**            | `List[A]`                   | `* -> *`       | Type with one type parameter                                               |
| **Intersection Types**  | `A & B`                     | `*`            | A type that conforms to both A and B                                       |
| **Union Types**         | `A | B`                     | `*`            | A type that can be either A or B                                           |
| **Algebraic Data Types**| `sealed trait Expr`         | `*`            | Combination of sum and product types                                       |
| **Variance**            | `+A`, `-A`                  | `* -> *`       | Variance annotations for subtyping behavior                                |
| **Opaque Types**        | `opaque type Meters = Int`  | `*`            | Abstracts implementation details from API                                 |
| **Structural Types**    | `{ def foo: Int }`          | `*`            | Duck typing with compile-time member checks                               |
| **Dependent Function Types** | `(x: A) => x.B`       | `*`            | Return type depends on the input value                                     |
| **Type Lambdas**        | `[X] =>> Either[String, X]` | `* -> *`       | Anonymous type functions                                                   |
| **Match Types**         | `Match[X] match { ... }`    | `* -> *`       | Pattern matching on types                                                  |
| **Existential Types**   | `List[_]`                   | `*`            | Type with hidden type parameter                                            |
| **Higher-Kinded Types** | `F[_]`, `F[_, _]`            | `* -> *`       | Types that take other types as arguments                                   |
| **Singleton Types**     | `x.type`                    | `*`            | A type that is the type of a specific value                                |
| **Refinement Types**    | `A { def x: Int }`          | `*`            | Adds members to existing types without extending them                      |
| **Kind Polymorphism**   | `F[_[_]]`                   | `k`            | Generalization over different kinds (`*`, `* -> *`, etc.)                  |

---

## Visual Kinds Summary

| Type                     | Kind             |
|--------------------------|------------------|
| `Int`                    | `*`              |
| `List`                   | `* -> *`         |
| `Either`                 | `* -> * -> *`    |
| `Functor[F[_]]`          | `(* -> *) -> *`  |
| `FunctorK[F[_[_]]]`      | `((* -> *) -> *) -> *` |

---

## Summary

Scala 3’s type system offers a rich set of tools for abstraction, modeling, and correctness. Understanding the distinctions between kinds and the features built on top of them (like higher-kinded types and kind polymorphism) is essential for advanced type-level programming.