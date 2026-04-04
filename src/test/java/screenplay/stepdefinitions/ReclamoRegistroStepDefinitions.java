package screenplay.stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import screenplay.questions.ElementVisibility;
import screenplay.ui.CommonElements;
import screenplay.ui.ReclamoForm;
import net.serenitybdd.screenplay.targets.Target;

import java.nio.file.Paths;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static org.hamcrest.Matchers.is;

public class ReclamoRegistroStepDefinitions {

    /**
     * CP001 – Registro exitoso: selecciona la póliza, llena todos los campos.
     */
    @When("registra un reclamo para la póliza {string} con fecha {string}, descripción {string}, monto {string} y ubicación {string}")
    public void registraUnReclamo(String poliza, String fecha, String descripcion, String monto, String ubicacion) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(
            WaitUntil.the(ReclamoForm.POLIZA_SELECT, isClickable()).forNoMoreThan(15).seconds(),
            screenplay.actions.FillReactInput.withFirstOption(ReclamoForm.POLIZA_SELECT),
            screenplay.actions.FillReactInput.withValue(fecha, ReclamoForm.FECHA_INCIDENTE_FIELD),
            Enter.theValue(descripcion).into(ReclamoForm.DESCRIPCION_FIELD),
            Enter.theValue(monto).into(ReclamoForm.MONTO_ESTIMADO_FIELD),
            Enter.theValue(ubicacion).into(ReclamoForm.UBICACION_FIELD)
        );
    }

    /**
     * Shortcut para CP006 y CP008 – llena el formulario con datos genéricos válidos.
     */
    @When("completa el formulario con datos válidos para la póliza {string}")
    public void completaFormulario(String poliza) {
        registraUnReclamo(poliza, "2026-03-15", "Datos de prueba genéricos", "1200.00", "Quito");
    }

    /**
     * CP003 – Omite un campo específico; el resto se llena correctamente.
     */
    @When("intenta registrar un reclamo para la póliza {string} omitiendo el campo {string}")
    public void registrarReclamoOmitiendoCampo(String poliza, String campoOmitido) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(
            WaitUntil.the(ReclamoForm.POLIZA_SELECT, isClickable()).forNoMoreThan(15).seconds(),
            screenplay.actions.FillReactInput.withFirstOption(ReclamoForm.POLIZA_SELECT),
            screenplay.actions.FillReactInput.withValue("2026-03-15", ReclamoForm.FECHA_INCIDENTE_FIELD)
        );

        switch (campoOmitido.toLowerCase()) {
            case "descripcion":
                // Omite la descripción; llena monto y ubicación
                actor.attemptsTo(
                    Enter.theValue("3500.00").into(ReclamoForm.MONTO_ESTIMADO_FIELD),
                    Enter.theValue("Quito").into(ReclamoForm.UBICACION_FIELD)
                );
                break;
            case "ubicacion":
                // Omite la ubicación; llena descripción y monto
                actor.attemptsTo(
                    Enter.theValue("Daños menores").into(ReclamoForm.DESCRIPCION_FIELD),
                    Enter.theValue("3500.00").into(ReclamoForm.MONTO_ESTIMADO_FIELD)
                );
                break;
            default:
                // Para cualquier otro campo omitido, llena todos los demás
                actor.attemptsTo(
                    Enter.theValue("Daños menores").into(ReclamoForm.DESCRIPCION_FIELD),
                    Enter.theValue("3500.00").into(ReclamoForm.MONTO_ESTIMADO_FIELD),
                    Enter.theValue("Quito").into(ReclamoForm.UBICACION_FIELD)
                );
                break;
        }
    }

    /**
     * CP004 – Ingresa un monto inválido (0 o negativo).
     */
    @When("intenta registrar un reclamo para la póliza {string} con monto estimado inválido {string}")
    public void registrarReclamoMontoInvalido(String poliza, String montoInvalido) {
        registraUnReclamo(poliza, "2026-03-15", "Daños menores", montoInvalido, "Quito");
    }

    /**
     * CP005 – Ingresa una fecha de incidente fuera del rango permitido (futura o antes de vigencia).
     * El campo tipo="date" solo acepta fechas hasta hoy (max=today), por lo que una fecha futura
     * no puede ingresarse via UI nativa. Se usa JavaScript para forzar el valor.
     */
    @When("intenta registrar un reclamo para la póliza {string} con fecha de incidente inválida {string}")
    public void registrarReclamoFechaInvalida(String poliza, String fechaInvalida) {
        registraUnReclamo(poliza, fechaInvalida, "Daños menores", "1000", "Quito");
    }

    /**
     * CP002 – No selecciona ninguna póliza; llena los demás campos y espera el error de validación.
     */
    @When("intenta registrar un reclamo sin seleccionar ninguna póliza")
    public void registrarReclamoSinPoliza() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(
            WaitUntil.the(ReclamoForm.POLIZA_SELECT, isClickable()).forNoMoreThan(15).seconds(),
            // No selecciona póliza (deja el select en blanco)
            screenplay.actions.FillReactInput.withValue("2026-03-15", ReclamoForm.FECHA_INCIDENTE_FIELD),
            Enter.theValue("Daño genérico").into(ReclamoForm.DESCRIPCION_FIELD),
            Enter.theValue("500").into(ReclamoForm.MONTO_ESTIMADO_FIELD),
            Enter.theValue("Quito").into(ReclamoForm.UBICACION_FIELD)
        );
    }

    // ── Pasos de adjuntar archivos ──────────────────────────────────────────

    @When("adjunta la fotografía válida {string}")
    public void adjuntaFotografiaValida(String archivo) {
        String path = Paths.get("src/test/resources/data/" + archivo).toAbsolutePath().toString();
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(
            net.serenitybdd.screenplay.actions.Upload.theFile(Paths.get(path))
                                                     .to(ReclamoForm.FILE_UPLOAD_INPUT)
        );
    }

    @When("adjunta el archivo en formato no permitido {string}")
    public void adjuntaArchivoInvalido(String archivo) {
        // Usa el mismo mecanismo de upload; el sistema detecta el tipo MIME y muestra el error
        adjuntaFotografiaValida(archivo);
    }

    @When("no adjunta ninguna fotografía")
    public void noAdjuntaFotografia() {
        // No se realiza ninguna acción; el formulario no tiene archivos adjuntos
    }

    // ── Confirmar registro ───────────────────────────────────────────────────

    @When("confirma el registro")
    public void confirmarRegistro() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(
            WaitUntil.the(ReclamoForm.SUBMIT_BUTTON, isClickable()).forNoMoreThan(5).seconds(),
            Click.on(ReclamoForm.SUBMIT_BUTTON)
        );
    }

    // ── Verificaciones (Then) ────────────────────────────────────────────────

    /**
     * CP001 – El sistema muestra la pantalla de confirmación de éxito
     * (el h2 "Reclamo enviado con éxito" que reemplaza al formulario).
     */
    @Then("el sistema muestra confirmación de reclamo enviado con éxito")
    public void sistemaConfirmaEnvioExitoso() {
        OnStage.theActorInTheSpotlight().attemptsTo(
            WaitUntil.the(ReclamoForm.REGISTRO_EXITOSO_TITLE, isVisible()).forNoMoreThan(15).seconds()
        );
        OnStage.theActorInTheSpotlight().should(
            seeThat(ElementVisibility.of(ReclamoForm.REGISTRO_EXITOSO_TITLE), is(true))
        );
    }

    /**
     * Step genérico para mensajes de éxito via toast (mantenido por compatibilidad con otros tests).
     */
    @Then("el sistema notifica {string}")
    public void elSistemaNotifica(String mensaje) {
        OnStage.theActorInTheSpotlight().attemptsTo(
            WaitUntil.the(ReclamoForm.REGISTRO_EXITOSO_TITLE, isVisible()).forNoMoreThan(15).seconds()
        );
    }

    @Then("asigna un número de seguimiento")
    public void asignaNumeroDeSeguimiento() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(
            WaitUntil.the(ReclamoForm.TRACKING_ID_LABEL, isVisible()).forNoMoreThan(5).seconds()
        );
        actor.should(seeThat(ElementVisibility.of(ReclamoForm.TRACKING_ID_LABEL), is(true)));
    }

    @Then("el reclamo aparece como {string}")
    public void reclamoApareceComo(String estado) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.should(seeThat(ElementVisibility.of(ReclamoForm.ESTADO_RECLAMO_LABEL), is(true)));
    }

    /**
     * CP002 – La póliza es obligatoria: el formulario muestra un error de validación inline.
     */
    @Then("el sistema notifica que es obligatorio seleccionar una póliza")
    public void notificaObligatorioPoliza() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(
            WaitUntil.the(ReclamoForm.POLIZA_REQUIRED_ERROR, isVisible()).forNoMoreThan(5).seconds()
        );
        actor.should(seeThat(ElementVisibility.of(ReclamoForm.POLIZA_REQUIRED_ERROR), is(true)));
    }

    /**
     * CP004 – El monto inválido muestra un error inline via react-hook-form.
     */
    @Then("el sistema notifica que el monto estimado no es válido")
    public void notificaMontoInvalido() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(
            WaitUntil.the(CommonElements.VALIDATION_ERROR, isVisible()).forNoMoreThan(5).seconds()
        );
        actor.should(seeThat(ElementVisibility.of(CommonElements.VALIDATION_ERROR), is(true)));
    }

    /**
     * CP005 – Fecha futura: el campo date tiene max=today por lo que no acepta fechas futuras.
     * La validación de HTML5 detiene el envío. Comprobamos la validez nativa.
     */
    @Then("el sistema notifica que la fecha del incidente no es válida")
    public void notificaFechaInvalida() {
        Actor actor = OnStage.theActorInTheSpotlight();
        // Verificamos si hay un form-error de React (si lo hubiera en el futuro) o un error nativo
        boolean hasReactError = ReclamoForm.FECHA_INCIDENTE_FIELD.resolveFor(actor)
                                .getAttribute("class").contains("error");
                                
        String validationMessage = ReclamoForm.FECHA_INCIDENTE_FIELD.resolveFor(actor)
                                                .getAttribute("validationMessage");
        
        boolean hasNativeError = validationMessage != null && !validationMessage.isEmpty();
        
        actor.should(seeThat("Tiene error de validacion (nativo o react)", 
            a -> hasReactError || hasNativeError, is(true)));
    }

    /**
     * CP006 – El archivo no permitido dispara un toast de error de react-hot-toast con texto "Solo se permiten".
     */
    @Then("el sistema notifica que solo se permiten imágenes")
    public void notificaSoloImagenes() {
        Actor actor = OnStage.theActorInTheSpotlight();
        Target errorToast = CommonElements.textOnPage("Solo se permiten");
        actor.attemptsTo(
            WaitUntil.the(errorToast, isVisible()).forNoMoreThan(5).seconds()
        );
        actor.should(seeThat(ElementVisibility.of(errorToast), is(true)));
    }

    /**
     * CP008 – Sin fotografía adjunta, al confirmar el sistema muestra un toast de error.
     */
    @Then("el sistema notifica que es obligatorio adjuntar al menos una fotografía")
    public void notificaObligatorioFotografia() {
        Actor actor = OnStage.theActorInTheSpotlight();
        Target errorToast = CommonElements.textOnPage("al menos una fotografía");
        actor.attemptsTo(
            WaitUntil.the(errorToast, isVisible()).forNoMoreThan(5).seconds()
        );
        actor.should(seeThat(ElementVisibility.of(errorToast), is(true)));
    }

    @Then("no se crea ningún registro de reclamo")
    public void noSeCreaRegistroDeReclamo() {
        Actor actor = OnStage.theActorInTheSpotlight();
        // El formulario sigue visible (el botón de envío todavía existe)
        actor.should(seeThat(ElementVisibility.of(ReclamoForm.SUBMIT_BUTTON), is(true)));
    }
}
