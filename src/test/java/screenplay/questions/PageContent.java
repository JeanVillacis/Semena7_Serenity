package screenplay.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import org.openqa.selenium.By;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

public class PageContent implements Question<String> {

    private static final PageContent INSTANCE = new PageContent();

    public static PageContent text() {
        return INSTANCE;
    }

    @Override
    public String answeredBy(Actor actor) {
        return BrowseTheWeb.as(actor).getDriver().findElement(By.tagName("body")).getText();
    }

    public static Question<Boolean> containsText(String expectedText) {
        return Question.about("la página contiene '" + expectedText + "'")
            .answeredBy(actor -> {
                String bodyText = BrowseTheWeb.as(actor).getDriver()
                    .findElement(By.tagName("body")).getText();
                return bodyText.contains(expectedText);
            });
    }
}
