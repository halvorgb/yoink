package be.halvorg.yoink
import Annotations.Resource

@Resource("what", "test", "")
case class What(test: Long)

trait Trait {
  val t: String
  val id: String

  val idValue: String

  val s: String
}

object Test extends App {
  Macros.hello
}
