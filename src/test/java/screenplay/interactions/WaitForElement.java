package screenplay.interactions;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;



import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class WaitForElement {

    public static Performable toBeVisible(Target target, int seconds) {
        return Task.where("{0} espera que " + target.getName() + " sea visible",
            WaitUntil.the(target, isVisible()).forNoMoreThan(seconds).seconds()
        );
    }

    public static Performable toBeVisible(Target target) {
        return toBeVisible(target, 10);
    }
}
