package views

import org.scalatest.{Matchers, FlatSpec}
import play.api.test._
import play.api.test.Helpers._
import java.util.Date

/**
  * Created by pnewman on 24/11/2015.
  */
class DebugSpec extends FlatSpec with Matchers{

  def testDebug {

    lazy val html = views.html.debug()

    "The debug view" should "render an html page" in {
      running(new FakeApplication()) {
        contentType(html) should be("text/html")
      }
    }

    it should "display the current date" in {
      contentAsString(html) should include(""+new Date())
    }
  }
  testDebug
}