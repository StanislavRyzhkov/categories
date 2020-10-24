package company.ryzhkov.instances

import company.ryzhkov.typeclasses.{Functor, Monoid}

class ListInstance[A] extends Monoid[List[A]] {
  override def mempty: List[A] = Nil

  override def mappend(a1: List[A], a2: List[A]): List[A] = a1 ::: a2
}

class ListFunctorInstance extends Functor[List] {
  override def fmap[A, B](fa: List[A])(f: A => B): List[B] = fa.map(f)
}
