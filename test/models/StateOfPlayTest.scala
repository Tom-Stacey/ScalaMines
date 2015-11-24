import models.{Square, StateOfPlay}
import org.scalatest.{Matchers, FlatSpec}


class StateOfPlayTest extends FlatSpec with Matchers  {

  "The StateOfPlay class" should "return the basic square picture location for an unclicked square" in {
      val stateOfPlay = new StateOfPlay()
      val unclickedSquare = new Square(true, false, false, Option(3))
    stateOfPlay.getSquareImageName(unclickedSquare) should be ("tile.png")
  }

  it should "return the correct number of surrounding mines for a clicked, unflagged square with four mines around" in {
    val stateOfPlay = new StateOfPlay()
    val unclickedSquare = new Square(false, false, true, Option(4))
    stateOfPlay.getSquareImageName(unclickedSquare) should be ("four.png")
  }

  // Square constructor - (containsMine:Boolean, flagged:Boolean, clicked:Boolean, surroundingMines:Option[Int])
}
