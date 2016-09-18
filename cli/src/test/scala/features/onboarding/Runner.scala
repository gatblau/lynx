package features.onboarding

import cucumber.api.CucumberOptions
import cucumber.api.junit.Cucumber
import org.junit.runner.RunWith

@RunWith(classOf[Cucumber])
@CucumberOptions(glue = Array[String]{ "features.onboarding" })
class Runner
