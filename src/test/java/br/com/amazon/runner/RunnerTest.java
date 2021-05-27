package br.com.amazon.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/br/com/amazon/features/amazon.feature",

        glue = "br.com.amazon.steps",
        tags = {"@Amazon"},

        plugin = {"pretty", "html:evidencias/html/evidence-html"},

        monochrome = true,

        snippets = CucumberOptions.SnippetType.CAMELCASE,

        dryRun = false,

        strict = true
)
public class RunnerTest {
}
