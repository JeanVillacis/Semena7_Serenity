package screenplay.actions;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class FillReactInput implements Interaction {
    private final String value;
    private final Target target;

    public FillReactInput(String value, Target target) {
        this.value = value;
        this.target = target;
    }

    public static FillReactInput withValue(String value, Target target) {
        return instrumented(FillReactInput.class, value, target);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebElement element = target.resolveFor(actor);
        JavascriptExecutor js = (JavascriptExecutor) Serenity.getDriver();
        js.executeScript("let setter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set; " +
            "if(arguments[0].tagName === 'SELECT') setter = Object.getOwnPropertyDescriptor(window.HTMLSelectElement.prototype, 'value').set; " +
            "setter.call(arguments[0], arguments[1]); " +
            "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
            "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));", element, value);
    }
}
