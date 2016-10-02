package features.content

import cucumber.api.CucumberOptions
import cucumber.api.junit.Cucumber
import org.junit.runner.RunWith

@RunWith(classOf[Cucumber])
@CucumberOptions(
  features = Array[String]{ "src/test/resources/features/survey/" },
  glue = Array[String]{ "features.survey" })
class Runner
