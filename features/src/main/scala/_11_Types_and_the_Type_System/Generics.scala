package _11_Types_and_the_Type_System

class Box[A](val value: A):
  def get: A = value

class Animal:
  def name = "animal"
class Dog extends Animal:
  override def name = "dog"
class Cat extends Animal:
  override def name = "cat"

class InvariantBox[A](var value: A)
class CovariantBox[+A](val value: A)
//class CovariantBox[+A](private val value: A):
//  def get: A = value

def upperBoundExample[A <: Comparable[A]](a1: A, a2: A): A =
  if a1.compareTo(a2) >= 0 then a1 else a2

class Printer[-A]:
  def print(value: A): Unit = println(value)

def maxList[A: Ordering](list: List[A]): A =
  list.max

trait Functor[F[_]]:
  def map[A, B](fa: F[A])(f: A => B): F[B]
