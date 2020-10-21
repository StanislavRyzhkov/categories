package company.ryzhkov.instances

import company.ryzhkov.typeclasses.Monoid

class BooleanAll extends Monoid[Boolean] {
  override def mempty: Boolean = true
  override def mappend(a1: Boolean, a2: Boolean): Boolean = a1 && a2
}

class BooleanAny extends Monoid[Boolean] {
  override def mempty: Boolean = false
  override def mappend(a1: Boolean, a2: Boolean): Boolean = a1 || a2
}
