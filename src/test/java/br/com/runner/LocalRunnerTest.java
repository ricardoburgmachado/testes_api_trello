package br.com.runner;
import br.com.utils.TestRule;
import org.junit.ClassRule;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(	features = "classpath:features",
        glue = {""},
        tags = "@Card",
        monochrome = false,
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        dryRun = false,
        plugin = { "pretty", "html:target/cucumber-reports" }
)
public class LocalRunnerTest  {

    @ClassRule
    public static TestRule testRule = new TestRule();

    public void start() {
        new LocalRunnerTest();
    }


}
