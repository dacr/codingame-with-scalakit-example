package mycode
import org.scalatest._
class MyCodeTest extends FlatSpec with Matchers {
  it should "pi constants must have the right value " in {
    math.Pi shouldBe 3.14 +- 0.01
  }
}

