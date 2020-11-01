package company.ryzhkov.instances

import company.ryzhkov.typeclasses.{Applicative, Functor}

object IdInstance {

  type Id[A] = A

  class IdFunctorInstance extends Functor[Id] {

    override def fmap[A, B](fa: Id[A])(f: A => B): Id[B] = f(fa)
  }

  class IdApplicative extends Applicative[Id] {
    override def pure[A](a: A): Id[A] = a

    override def ap[A, B](fa: Id[A])(fab: Id[A => B]): Id[B] = fab(fa)
  }
}
