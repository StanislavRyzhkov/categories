package company.ryzhkov.typeclasses

trait Functor[F[_]] {
  def fmap[A, B](fa: F[A])(f: A => B): F[B]
}

object Functor {

  implicit class FunctorOps[A, F[_]: Functor](fa: F[A]) {
    val param: Functor[F] = implicitly[Functor[F]]

    def fmap[B](f: A => B): F[B] = param.fmap(fa)(f)
  }
}
