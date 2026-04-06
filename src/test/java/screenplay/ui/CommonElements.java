package screenplay.ui;

import net.serenitybdd.screenplay.targets.Target;

public class CommonElements {

    public static final Target SUCCESS_ALERT = Target.the("alerta de éxito")
        .locatedBy("//*[@role='status' and (contains(text(),'exitosamente') or contains(text(),'correctamente') or contains(text(),'registrado'))]"
            + " | //*[contains(text(),'exitosamente') or contains(text(),'correctamente')]");

    public static final Target TOAST_ERROR = Target.the("toast de error (react-hot-toast)")
        .locatedBy("//*[@role='alert'] | //*[contains(@class,'go') and contains(@class,'alert')]");

    public static final Target VALIDATION_ERROR = Target.the("error de validación inline")
        .locatedBy("//*[contains(@class,'form-error') and string-length(normalize-space(text())) > 0]");

    public static final Target VALIDATION_ERROR_INPUT = Target.the("input con error de validación")
        .locatedBy("//input[contains(@class,'error')] | //textarea[contains(@class,'error')]");

    public static Target textOnPage(String text) {
        return Target.the("texto '" + text + "' en la página")
            .locatedBy("//*[contains(text(),'" + text + "')]");
    }
}
