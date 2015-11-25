package views

import models.MineSweeperGrid
import org.scalatest.{Matchers, FlatSpec}
import play.api.test._
import play.api.test.Helpers._
import play.api.test.FakeApplication

/**
  * Created by pnewman on 24/11/2015.
  */
class MinefieldSpec extends FlatSpec with Matchers{

  def testMinefield: Unit ={

    val rows = 5
    val cols = 10

    val minefield = new MineSweeperGrid(rows)

    //lazy val html = views.html.minefield(rows,cols)
    lazy val html = views.html.minefield(minefield)

    "The minefield view" should "render an html page" in{
      new FakeApplication(){
        contentType(html) should be ("text/html")
      }
    }

    it should "be embedded in the main view with title \"Minesweeper\" " in {
      new FakeApplication() {
        contentAsString(html) should include("<title>Minesweeper</title>")

      }
    }

    it should "include a table" in {
      contentAsString(html) should include("<table class=\"table table-bordered table-hover\">")
    }

    it should "have at given number of table rows" in {
      contentAsString(html) should include ("<tr id=\"1\">")
      contentAsString(html) should include ("<tr id=\"" +minefield.rows+ "\">")
    }

    it should "have a given number of table cells per row" in {
      contentAsString(html) should include ("<td id=\"1\">")
      contentAsString(html) should include ("<td id=\"" +minefield.rows+ "\">")
    }

  }

  testMinefield
}
