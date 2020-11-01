package company.ryzhkov.instances

import company.ryzhkov.typeclasses.Functor

class FunctionInstance[T] extends Functor[T => *] {
  override def fmap[A, B](fa: T => A)(f: A => B): T => B = fa andThen f
}
