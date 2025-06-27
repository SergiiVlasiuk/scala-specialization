package _11_Types_and_the_Type_System

import scala.reflect.Selectable.reflectiveSelectable

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class StructuralTypeSpec extends AnyFlatSpec with Matchers {

  "closeQuietly" should "call close method on File" in {
    val file: {def close(): Unit; def closed: Boolean} = new {
      var closed = false
      def close(): Unit = closed = true
    }
    closeQuietly(file)
    file.closed shouldBe true
  }

  it should "call close method on Connection" in {
    val conn: {def close(): Unit; def closed: Boolean} = new {
      var closed = false
      def close(): Unit = closed = true
    }
    closeQuietly(conn)
    conn.closed shouldBe true
  }

  "printName" should "print the name property" in {
    val out = new java.io.ByteArrayOutputStream()
    Console.withOut(out) {
      val entity: { def name: String } = new { val name = "TestName" }
      printName(entity)
    }
    out.toString should include ("TestName")
  }

  "invokeClose" should "call close on Selectable resource" in {
    class TestResource extends Selectable {
      var closed = false

      def close(): Unit = closed = true
    }

    val res = new TestResource
    invokeClose(res)
    res.closed shouldBe true
  }
}
