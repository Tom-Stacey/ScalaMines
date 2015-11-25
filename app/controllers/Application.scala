package controllers

import models.MineSweeperGrid
import play.api.mvc._

class Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def entry = Action {
    Ok(views.html.entry("Click it!!"))
  }

  def sayHi = Action {
    Ok(views.html.sayHi())
  }

  /**
    * Test method for minesweeper page
    * @return
    */
  def minesTest = Action {
    val grid = new MineSweeperGrid(10)
    grid.makeInitialGrid()
    Ok(views.html.minefield(grid))
  }
}