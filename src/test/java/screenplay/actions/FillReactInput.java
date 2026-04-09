package screenplay.actions;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;
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

    public static FillReactInput withFirstOption(Target target) {
        return instrumented(FillReactInput.class, "FIRST_OPTION", target);
    }

    public static FillReactInput withOptionContaining(String text, Target target) {
        return instrumented(FillReactInput.class, "OPTION_TEXT:" + text, target);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebElement element = target.resolveFor(actor);
        String actualValue = value;
        
        if ("FIRST_OPTION".equals(value) && "select".equalsIgnoreCase(element.getTagName())) {
            actualValue = element.findElements(By.tagName("option")).get(1).getAttribute("value");
        } else if (value.startsWith("OPTION_TEXT:") && "select".equalsIgnoreCase(element.getTagName())) {
            String text = value.substring("OPTION_TEXT:".length());
            actualValue = element.findElements(By.tagName("option")).stream()
                .filter(o -> o.getText().contains(text))
                .map(o -> o.getAttribute("value"))
                .findFirst()
                .orElse("");
        }
        
        JavascriptExecutor js = (JavascriptExecutor) Serenity.getDriver();
        js.executeScript("let setter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set; " +
            "if(arguments[0].tagName === 'SELECT') setter = Object.getOwnPropertyDescriptor(window.HTMLSelectElement.prototype, 'value').set; " +
            "setter.call(arguments[0], arguments[1]); " +
            "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
            "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));", element, actualValue);
    }
}
