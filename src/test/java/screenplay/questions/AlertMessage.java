package screenplay.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;
import screenplay.ui.CommonElements;

public class AlertMessage implements Question<String> {

    private final Target alertTarget;

    private AlertMessage(Target alertTarget) {
        this.alertTarget = alertTarget;
    }

    public static AlertMessage ofSuccess() {
        return new AlertMessage(CommonElements.SUCCESS_ALERT);
    }

    public static AlertMessage ofError() {
        return new AlertMessage(CommonElements.ERROR_ALERT);
    }

    public static AlertMessage of(Target target) {
        return new AlertMessage(target);
    }

    @Override
    public String answeredBy(Actor actor) {
        try {
            return alertTarget.resolveFor(actor).getText();
        } catch (Exception e) {
            return "";
        }
    }
}
