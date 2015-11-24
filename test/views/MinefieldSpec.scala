package views

import org.scalatest.{Matchers, FlatSpec}
import play.api.test._
import play.api.test.Helpers._
import play.api.test.FakeApplication

/**
  * Created by pnewman on 24/11/2015.
  */
class MinefieldSpec extends FlatSpec with Matchers{

  def testMinefield: Unit ={

    lazy val html = views.html.minefield()

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

    it should "have at least one table cell" in {
      contentAsString(html) should include ("<td>")
    }

  }

  testMinefield
}
