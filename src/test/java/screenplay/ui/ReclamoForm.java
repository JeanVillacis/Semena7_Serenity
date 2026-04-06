package screenplay.ui;

import net.serenitybdd.screenplay.targets.Target;

public class ReclamoForm {

    public static final Target POLIZA_SELECT = Target.the("selector de Póliza")
        .locatedBy("//select[@name='polizaId']");

    public static final Target FECHA_INCIDENTE_FIELD = Target.the("campo Fecha del Incidente")
        .locatedBy("//input[@name='fechaIncidente']");

    public static final Target DESCRIPCION_FIELD = Target.the("campo Descripción")
        .locatedBy("//textarea[@name='descripcion']");

    public static final Target MONTO_ESTIMADO_FIELD = Target.the("campo Monto Estimado")
        .locatedBy("//input[@name='montoEstimado']");

    public static final Target UBICACION_FIELD = Target.the("campo Ubicación")
        .locatedBy("//input[@name='ubicacion']");

    public static final Target FILE_UPLOAD_INPUT = Target.the("input de subida de evidencia")
        .locatedBy("//input[@type='file']");

    public static final Target SUBMIT_BUTTON = Target.the("botón Enviar Reclamo")
        .locatedBy("//button[@type='submit']");

    public static final Target REGISTRO_EXITOSO_TITLE = Target.the("título Reclamo enviado con éxito")
        .locatedBy("//h2[contains(text(),'Reclamo enviado con') or contains(text(),'xito')]");

    public static final Target TRACKING_ID_LABEL = Target.the("etiqueta Tracking ID")
        .locatedBy("//p[contains(@class,'font-mono')]");

    public static final Target ESTADO_RECLAMO_LABEL = Target.the("etiqueta de estado del reclamo")
        .locatedBy("//span[contains(@class,'rounded-full') and (contains(text(),'Recibido') or contains(text(),'REGISTRADO'))]");

    public static final Target POLIZA_REQUIRED_ERROR = Target.the("error póliza obligatoria")
        .locatedBy("//*[contains(@class,'form-error') and (contains(text(),'Seleccione') or contains(text(),'poliza') or contains(text(),'póliza'))]");

    public static final Target SIN_POLIZAS_ACTIVAS_ERROR = Target.the("error sin pólizas activas")
        .locatedBy("//*[contains(@class,'form-error') and contains(text(),'No tiene p')]");
}
