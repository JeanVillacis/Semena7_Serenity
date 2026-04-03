package screenplay.ui;

import net.serenitybdd.screenplay.targets.Target;

public class AseguradoForm {

    public static final Target MODAL_TITLE = Target.the("título modal Registrar Asegurado")
        .locatedBy("//*[contains(text(),'Registrar Asegurado')]");

    public static final Target NOMBRE_FIELD = Target.the("campo Nombre")
        .locatedBy("//input[@placeholder='Juan']");

    public static final Target APELLIDO_FIELD = Target.the("campo Apellido")
        .locatedBy("//input[@placeholder='Pérez']");

    public static final Target IDENTIFICACION_FIELD = Target.the("campo Identificación")
        .locatedBy("//input[@placeholder='1712345678']");

    public static final Target CORREO_FIELD = Target.the("campo Correo Electrónico")
        .locatedBy("//input[@placeholder='juan@email.com']");

    public static final Target TELEFONO_FIELD = Target.the("campo Teléfono")
        .locatedBy("//input[@placeholder='0991234567']");

    public static final Target DIRECCION_FIELD = Target.the("campo Dirección")
        .locatedBy("//input[@placeholder='Av. Amazonas N36-152']");

    public static final Target SUBMIT_BUTTON = Target.the("botón Registrar Asegurado")
        .locatedBy("//button[text()='Registrar Asegurado']");

    public static final Target CLOSE_BUTTON = Target.the("botón cerrar modal")
        .locatedBy("//*[contains(text(),'Registrar Asegurado')]/ancestor::div[contains(@class,'modal') or contains(@class,'fixed')]//button[contains(@class,'close') or contains(text(),'×') or contains(text(),'✕')]");
}
