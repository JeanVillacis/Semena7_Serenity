package screenplay.ui;

import net.serenitybdd.screenplay.targets.Target;

public class PolizaForm {

    public static final Target MODAL_TITLE = Target.the("título modal Registrar Nueva Póliza")
        .locatedBy("//*[contains(text(),'Registrar') and contains(text(),'liza')]");

    public static final Target NUMERO_POLIZA_FIELD = Target.the("campo Número de Póliza")
        .locatedBy("//input[@placeholder='POL-2026-001']");

    public static final Target ASEGURADO_SELECT = Target.the("selector de Asegurado")
        .locatedBy("//select[@name='aseguradoId']");

    public static final Target VEHICULO_SELECT = Target.the("selector de Vehículo")
        .locatedBy("//select[@name='vehiculoId']");

    public static final Target VALOR_ASEGURADO_FIELD = Target.the("campo Valor Asegurado")
        .locatedBy("//input[@placeholder='25000.00']");

    public static final Target VIGENCIA_INICIO_FIELD = Target.the("campo Vigencia Inicio")
        .locatedBy("(//input[@type='date'])[1]");

    public static final Target VIGENCIA_FIN_FIELD = Target.the("campo Vigencia Fin")
        .locatedBy("(//input[@type='date'])[2]");

    public static final Target SUBMIT_BUTTON = Target.the("botón Registrar Póliza")
        .locatedBy("//button[contains(text(),'Registrar') and contains(text(),'liza')]");
}
