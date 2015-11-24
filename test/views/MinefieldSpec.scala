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
      running(new FakeApplication()){
        contentType(html) should be ("text/html")
      }
    }

    it should "include a table" in {
      running(new FakeApplication()) {
        contentAsString(html) should include("<table>")
        contentAsString(html) should include("</table>")
      }
    }

  }
  testMinefield
}
