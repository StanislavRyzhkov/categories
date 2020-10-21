package company.ryzhkov

import company.ryzhkov.typeclasses.Monoid
import company.ryzhkov.typeclasses.Monoid.MonoidOps
import org.scalatest.funsuite.AnyFunSuite

class MonoidTest extends AnyFunSuite {

  test("booleanAll") {
    import company.ryzhkov.implicits.booleanMonoidAll

    val a = true
    val b = true
    val c = false
    val e = Monoid[Boolean].mempty

    assert((a |+| e) == a)
    assert((e |+| a) == a)

    assert(((a |+| b) |+| c) == (a |+| (b |+| c)))
  }

  test("list") {
    import company.ryzhkov.implicits.listMonoid

    val a = List(1, 2)
    val b = List[Int]()
    val c = List(3, 4)
    val e = Monoid[List[Int]].mempty

    assert((a |+| e) == a)
    assert((e |+| a) == a)

    assert(((a |+| b) |+| c) == (a |+| (b |+| c)))

    assert(List(1, 2, 3, 4) == (a |+| b |+| c))
  }

  test("tuple") {
    import company.ryzhkov.implicits.{intAdditionalMonoid, tupleMonoid}

    val a = (1, 2)
    val b = (3, 4)
    val c = (5, 6)
    val e = Monoid[(Int, Int)].mempty

    assert((a |+| e) == a)
    assert((e |+| a) == a)

    assert(((a |+| b) |+| c) == (a |+| (b |+| c)))

    assert((9, 12) == (a |+| b |+| c))
  }

  test("option") {
    import company.ryzhkov.implicits.optionMonoid
    import company.ryzhkov.implicits.intAdditionalMonoid

    val a: Option[Int] = Some(1)
    val b: Option[Int] = Some(2)
    val c: Option[Int] = Some(3)
    val e: Option[Int] = Monoid[Option[Int]].mempty

    assert((a |+| e) == a)
    assert((e |+| a) == a)

    assert(((a |+| b) |+| c) == (a |+| (b |+| c)))

    assert((a |+| b |+| e |+| c).contains(6))
  }

  test("optionFirst") {
    import company.ryzhkov.implicits.optionFirstMonoid

    val a: Option[Int] = None
    val b: Option[Int] = Some(2)
    val c: Option[Int] = Some(3)
    val e: Option[Int] = Monoid[Option[Int]].mempty

    assert((a |+| e) == a)
    assert((e |+| a) == a)

    assert(((a |+| b) |+| c) == (a |+| (b |+| c)))

    assert((a |+| b |+| e |+| c).contains(2))
  }

  test("endo") {
    import company.ryzhkov.implicits.endoMonoid

    val a: String => String = _.toUpperCase()
    val b: String => String = identity
    val c: String => String = _ + "!"
    val e: String => String = Monoid[String => String].mempty

    assert((a |+| e)("a") == a("a"))
    assert((e |+| a)("a") == a("a"))

    assert(((a |+| b) |+| c)("a") == (a |+| (b |+| c))("a"))

    assert(("ABC!") == (a |+| b |+| c)("abc"))
  }
}
