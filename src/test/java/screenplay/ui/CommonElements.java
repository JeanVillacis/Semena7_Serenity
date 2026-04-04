package screenplay.ui;

import net.serenitybdd.screenplay.targets.Target;

public class CommonElements {

    /**
     * Toast de éxito de react-hot-toast.
     * La librería renderiza mensajes de éxito dentro del árbol del Toaster con role="status".
     */
    public static final Target SUCCESS_ALERT = Target.the("alerta de éxito")
        .locatedBy("//*[@role='status' and (contains(text(),'exitosamente') or contains(text(),'correctamente') or contains(text(),'registrado'))]"
            + " | //*[contains(text(),'exitosamente') or contains(text(),'correctamente')]");

    /**
     * Toast de error de react-hot-toast.
     * react-hot-toast renderiza los mensajes en un div con atributo data-type o en un contenedor
     * dinámico. El selector más robusto es buscar el texto del toast directamente.
     * Estructura en el DOM: <div role="alert">...mensaje...</div>
     */
    public static final Target TOAST_ERROR = Target.the("toast de error (react-hot-toast)")
        .locatedBy("//*[@role='alert'] | //*[contains(@class,'go') and contains(@class,'alert')]");

    /**
     * Error genérico de validación inline (renderizado por react-hook-form como .form-error).
     * Ejemplo: <p class="form-error">La descripción es obligatoria</p>
     */
    public static final Target VALIDATION_ERROR = Target.the("error de validación inline")
        .locatedBy("//*[contains(@class,'form-error') and string-length(normalize-space(text())) > 0]");

    /**
     * Input que tiene clase 'error' aplicada por react-hook-form cuando falla la validación.
     */
    public static final Target VALIDATION_ERROR_INPUT = Target.the("input con error de validación")
        .locatedBy("//input[contains(@class,'error')] | //textarea[contains(@class,'error')]");

    /**
     * Helper para verificar si un texto específico aparece en la página.
     */
    public static Target textOnPage(String text) {
        return Target.the("texto '" + text + "' en la página")
            .locatedBy("//*[contains(text(),'" + text + "')]");
    }
}
