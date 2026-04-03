package screenplay.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Clear;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class SearchInList implements Task {

    private final Target searchField;
    private final String searchText;

    public SearchInList(Target searchField, String searchText) {
        this.searchField = searchField;
        this.searchText = searchText;
    }

    public static Performable byText(Target searchField, String searchText) {
        return instrumented(SearchInList.class, searchField, searchText);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            WaitUntil.the(searchField, isVisible()).forNoMoreThan(10).seconds(),
            Clear.field(searchField),
            Enter.theValue(searchText).into(searchField)
        );
    }
}
