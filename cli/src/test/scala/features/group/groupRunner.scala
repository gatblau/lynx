package features.group

import cucumber.api.CucumberOptions
import cucumber.api.junit.Cucumber
import org.junit.runner.RunWith

@CucumberOptions(
  features = Array("src/test/resources/features/group/CreateGroup.feature"),
  glue = Array("src/test/scala/features/group")
)
@RunWith(classOf[Cucumber])
class groupRunner
