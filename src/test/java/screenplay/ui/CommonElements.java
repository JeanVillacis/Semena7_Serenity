package screenplay.ui;

import net.serenitybdd.screenplay.targets.Target;

public class CommonElements {

    public static final Target SUCCESS_ALERT = Target.the("alerta de éxito")
        .locatedBy("//*[contains(text(),'exitosamente') or contains(text(),'correctamente') or contains(text(),'registrado')]");

    public static final Target ERROR_ALERT = Target.the("alerta de error")
        .locatedBy("//*[contains(@class,'toast-error') or contains(@class,'bg-red') or (contains(@class,'toast') and contains(text(),'error'))]");

    public static final Target VALIDATION_ERROR = Target.the("error de validación")
        .locatedBy("//*[contains(@class,'text-red') or contains(@class,'error-message')]");

    public static final Target VALIDATION_ERROR_INPUT = Target.the("input con error de validación")
        .locatedBy("//input[contains(@class,'error') or contains(@class,'border-red')]");

    public static Target textOnPage(String text) {
        return Target.the("texto '" + text + "' en la página")
            .locatedBy("//*[contains(text(),'" + text + "')]");
    }
}
