package models

import scala.util.Random
/**
  * @author tstacey
  */
class MineSweeperGrid(rows:Int, cols:Int, numberOfMines:Int) {
  def this(size:Int) = this(size, size, size.*(2))
  val grid:Array[Array[Square]] = Array.fill(rows, cols)(new Square())

  def makeInitialGrid():Array[Array[Square]] = {
    placeMines()
    setSurroundingValues()
    grid;
  }

  def placeMines():Unit = {

    def loop(minesPlaced:Int):Unit = {
      val rowCoord = Random.nextInt(rows)
      val colCoord = Random.nextInt(cols)
      if(minesPlaced == numberOfMines) {
        // END LOOP
      } else if(grid(rowCoord)(colCoord).containsMine) {
        loop(minesPlaced)
      } else {
        grid(rowCoord)(colCoord) = grid(rowCoord)(colCoord).copy(containsMine = true)
        loop(minesPlaced.+(1))
      }
    }

    loop(0)
  }

  /**
    * sets the surrounding mines values for all squares in the grid
    */
  def setSurroundingValues():Unit = {

    def loop(row:Int, col:Int):Unit = {
      grid(row)(col) = grid(row)(col).copy(surroundingMines = Option(calculateSurroundingMines(row, col)))
      if(row == rows.-(1) && col == cols.-(1)) {
        // END LOOP
      } else {
        if(col < cols.-(1)) {
          loop(row, col.+(1))
        } else {
          loop(row.+(1), 0)
        }
      }
    }

    loop(0,0)

  }

  /**
    * returns the number of mines surrounding the square at the passed row and column
    */
  private def calculateSurroundingMines(row:Int, col:Int):Int = {

    def loop(currentRow:Int, currentCol:Int, total:Int):Int = {
      if(currentRow == row.+(1) && currentCol == col.+(1)) {
        if(!boundaryCheck(currentRow,currentCol)) {
          total
        } else {
          total.+( grid(currentRow)(currentCol).mineCount() )
        }
      } else {
        if(currentCol < col.+(1)) {
          if(boundaryCheck(currentRow,currentCol)) {
            loop(currentRow, currentCol.+(1), total)
          } else {
            loop(currentRow, currentCol.+(1), total.+(grid(currentRow)(currentCol).mineCount()))
          }
        } else {
          if(boundaryCheck(currentRow,currentCol)) {
            loop(currentRow.+(1), col.-(1), total)
          } else {
            loop(currentRow.+(1), col.-(1), total.+(grid(currentRow)(currentCol).mineCount()))
          }
        }
      }
    }

    loop(row.-(1), col.-(1), 0)

  }

  /**
    * returns false if coords are out of bounds
    */
  def boundaryCheck(chkRow:Int, chkCol:Int):Boolean = {
    if(chkRow < 0 || chkCol < 0 || chkRow >= rows || chkCol >= cols) {
      false
    } else {
      true
    }
  }

  /**
    * marks a space as clicked and clicks around it if there are 0 mines surrounding it
    * returns true if the space contains a mine, false if not
    */
  def click(row:Int, col:Int):Boolean = {
    if(!grid(row)(col).clicked && boundaryCheck(row, col)) {
      grid(row)(col).copy(clicked = true)
      if(grid(row)(col).containsMine) {
        true
      } else {
        if(calculateSurroundingMines(row, col) == 0) {
          clickAround(row,col)
        }
        false
      }
    } else {
      false
    }
  }

  /**
    * clicks all cells surrounding a mine in order to open rooms where appropriate
    * @param row
    * @param col
    */
  def clickAround(row:Int, col:Int) {
    def loop(currentRow:Int, currentCol:Int):Unit = {
      click(currentRow, currentCol)
      if(currentRow == row.+(1) && currentCol == col.+(1)) {
        // CLOSE LOOP
      } else {
        if(currentCol < col.+(1)) {
          loop(currentRow, currentCol.+(1))
        } else {
          loop(currentRow.+(1), col.-(1))
        }
      }
    }
    loop(row.-(1), col.-(1))
  }

  /**
    * reverses the flagged status of the square at the corresponding row and column
    * @param row
    * @param col
    */
  def flagToggle(row:Int, col:Int) {
    if(!grid(row)(col).clicked) {
      grid(row)(col).copy(flagged = !grid(row)(col).flagged)
    }
  }


}