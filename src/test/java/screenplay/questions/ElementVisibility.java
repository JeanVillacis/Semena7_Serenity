package screenplay.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;

public class ElementVisibility implements Question<Boolean> {

    private final Target target;

    private ElementVisibility(Target target) {
        this.target = target;
    }

    public static ElementVisibility of(Target target) {
        return new ElementVisibility(target);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        try {
            return target.resolveFor(actor).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
