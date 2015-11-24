package models

import scala.collection.immutable.HashMap

/**
  * Created by tstacey on 24/11/2015.
  */
class StateOfPlay {

  val clickedSurroundingImageLocs = HashMap(0 -> "blank.png", 1 -> "one.png",
                                            2 -> "two.png",   3 -> "three.png",
                                            4 -> "four.png",  5 -> "five.png",
                                            6 -> "six.png",   7 -> "seven.png",
                                            8 -> "eight.png")

  val flaggedImageLoc = "flagged.png"
  val unclickedImageLoc = "tile.png"
  val mineExposedImageLoc = "mine.png"

  def getSquareImageName(square: Square): String = {
    if(square.flagged) {
      flaggedImageLoc
    } else if (!square.clicked) {
      unclickedImageLoc
    } else if(square.containsMine) {
      mineExposedImageLoc
    } else clickedSurroundingImageLocs.getOrElse(square.surroundingMines.getOrElse(0), "blank.png")
  }
}
