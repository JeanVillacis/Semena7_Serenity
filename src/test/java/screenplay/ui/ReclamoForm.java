package screenplay.ui;

import net.serenitybdd.screenplay.targets.Target;

public class ReclamoForm {

    // ── Campos del formulario ────────────────────────────────────────────────

    /** Selector de póliza activa (select nativo de react-hook-form). */
    public static final Target POLIZA_SELECT = Target.the("selector de Póliza")
        .locatedBy("//select[@name='polizaId']");

    /** Campo de fecha del incidente (input[type=date] con name='fechaIncidente'). */
    public static final Target FECHA_INCIDENTE_FIELD = Target.the("campo Fecha del Incidente")
        .locatedBy("//input[@name='fechaIncidente']");

    /** Área de texto de descripción. */
    public static final Target DESCRIPCION_FIELD = Target.the("campo Descripción")
        .locatedBy("//textarea[@name='descripcion']");

    /** Campo de monto estimado. */
    public static final Target MONTO_ESTIMADO_FIELD = Target.the("campo Monto Estimado")
        .locatedBy("//input[@name='montoEstimado']");

    /** Campo de ubicación del incidente. */
    public static final Target UBICACION_FIELD = Target.the("campo Ubicación")
        .locatedBy("//input[@name='ubicacion']");

    /** Input oculto para adjuntar archivos de evidencia. */
    public static final Target FILE_UPLOAD_INPUT = Target.the("input de subida de evidencia")
        .locatedBy("//input[@type='file']");

    /** Botón de envío del formulario "Enviar Reclamo →". */
    public static final Target SUBMIT_BUTTON = Target.the("botón Enviar Reclamo")
        .locatedBy("//button[@type='submit']");

    // ── Pantalla de éxito ────────────────────────────────────────────────────

    /**
     * Título de la pantalla de confirmación mostrada tras un registro exitoso.
     * Coincide con: <h2>Reclamo enviado con éxito</h2>
     */
    public static final Target REGISTRO_EXITOSO_TITLE = Target.the("título Reclamo enviado con éxito")
        .locatedBy("//h2[contains(text(),'Reclamo enviado con') or contains(text(),'xito')]");

    /**
     * Número de seguimiento (Tracking ID) mostrado en la pantalla de éxito.
     * El frontend lo renderiza dentro de un <p class="... font-mono ...">
     */
    public static final Target TRACKING_ID_LABEL = Target.the("etiqueta Tracking ID")
        .locatedBy("//p[contains(@class,'font-mono')]");

    /**
     * Badge de estado "Recibido" que aparece en la tarjeta de éxito.
     * Coincide con: <span class="... rounded-full ...">Recibido</span>
     */
    public static final Target ESTADO_RECLAMO_LABEL = Target.the("etiqueta de estado del reclamo")
        .locatedBy("//span[contains(@class,'rounded-full') and (contains(text(),'Recibido') or contains(text(),'REGISTRADO'))]");

    // ── Errores de validación inline ─────────────────────────────────────────

    /**
     * Mensaje de error inline cuando no se selecciona una póliza.
     * El frontend renderiza: <p class="form-error">Seleccione una póliza activa</p>
     */
    public static final Target POLIZA_REQUIRED_ERROR = Target.the("error póliza obligatoria")
        .locatedBy("//*[contains(@class,'form-error') and (contains(text(),'Seleccione') or contains(text(),'poliza') or contains(text(),'póliza'))]");

    /**
     * Mensaje de error cuando no hay pólizas activas disponibles para el asegurado.
     * El frontend renderiza: <p class="form-error">No tiene pólizas activas disponibles.</p>
     */
    public static final Target SIN_POLIZAS_ACTIVAS_ERROR = Target.the("error sin pólizas activas")
        .locatedBy("//*[contains(@class,'form-error') and contains(text(),'No tiene p')]");
}
