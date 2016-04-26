package be.halvorg.yoink

import scala.language.experimental.macros
import scala.reflect.macros.whitebox.Context
import scala.annotation.{ StaticAnnotation, compileTimeOnly }

object Annotations {
  @compileTimeOnly("enable macro paradise to expand macro annotations.")
  class Resource(t: String, id: String, s: String) extends StaticAnnotation {
    def macroTransform(annottees: Any*): Any = macro validateSchemaImpl

  }

  def validateSchemaImpl(c: Context)(annottees: c.Expr[Any]*): c.Expr[Any] = {
    import c.universe._

    // Unpack parameters to resource
    val params: (String, String, String) = c.prefix.tree match {
      case q"new Resource($t, $id, $s)" =>
        (
          c.eval[String](c.Expr(t)),
          c.eval[String](c.Expr(id)),
          c.eval[String](c.Expr(s))
        )
    }

    println(params)
    //annottees.map(_.tree) match {
    //  case t => {
    //    c.abort(c.enclosingPosition, s"Invalid annottee, is your schema a case object?, see: ${showRaw(t)}")
    //  }
    //}
    c.abort(c.enclosingPosition, s"placeholder, ${showRaw(annottees)}")
  }
}
