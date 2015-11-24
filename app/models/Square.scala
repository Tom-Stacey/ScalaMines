package models

  /**
  * @author tstacey
  * Model for a single square on the minesweeper grid
  */

case class Square(containsMine:Boolean, flagged:Boolean, clicked:Boolean, surroundingMines:Option[Int]) {
  def this() = this(false,false,false,None)

  /**
    * returns the number of mines in the square
    */
  def mineCount():Int = {
    if(containsMine) 1 else 0
  }

}