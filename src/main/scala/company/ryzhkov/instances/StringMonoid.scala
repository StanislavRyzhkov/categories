package company.ryzhkov.instances

import company.ryzhkov.typeclasses.Monoid

class StringMonoid extends Monoid[String] {
  override def mempty: String = ""

  override def mappend(a1: String, a2: String): String = a1.concat(a2)
}
