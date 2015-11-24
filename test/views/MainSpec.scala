package views

import controllers.routes
import org.scalatest.{Matchers, FlatSpec}
import play.api.test.Helpers._
import play.api.test.FakeApplication

/**
  * Created by pnewman on 24/11/2015.
  */
class MainSpec extends FlatSpec with Matchers{

  def testMain: Unit ={

    lazy val html = views.html.main("Hello, World!")(views.html.index("Test message"))
    lazy val bootstrapCss = routes.Assets.versioned("stylesheets/bootstrap.css")
    lazy val bootstrapJs = routes.Assets.versioned("javascripts/bootstrap.js")

    "The main view" should "render an html page" in{
      running(new FakeApplication()){
        contentType(html) should be ("text/html")
      }
    }

    it should "import the ajax javascript library" in{
      contentAsString(html) should include ("<script src = \"http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js\"></script>")
    }

    it should "import the required bootstrap-3 css" in{
      contentAsString(html) should include ("<link rel=\"stylesheet\" media=\"screen\" href=\"" + bootstrapCss + "\">")
    }

    it should "import the bootstrap-3 javascript library" in{
      contentAsString(html) should include ("<script src=\"" + bootstrapJs + "\" type=\"text/javascript\"></script>")
    }

    it should "contain the given content html" in{
      running (new FakeApplication()) {
        contentAsString(html) should include(contentAsString(views.html.index("Test message")))
      }
    }

    it should "contain the debug html" in {
      running (new FakeApplication()) {
        contentAsString(html) should include(contentAsString(views.html.debug()))
      }
    }

  }

  testMain
}
