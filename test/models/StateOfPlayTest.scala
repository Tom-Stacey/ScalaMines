import models.{Square, StateOfPlay}
import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._

/**
  * Add your spec here.
  * You can mock out a whole application including requests, plugins etc.
  * For more information, consult the wiki.
  */
@RunWith(classOf[JUnitRunner])
class StateOfPlayTest extends Specification {

  "The StateOfPlay class" should {"return the basic square picture location for an unclicked square" in {
      val stateOfPlay = new StateOfPlay()
      val unclickedSquare = new Square(true, false, false, Option(3))
    stateOfPlay.getSquareImageName(unclickedSquare) should be ("tile.png")
    }
  }
}
