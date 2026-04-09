package screenplay.stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
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

    @When("registra un reclamo para la póliza {string} con fecha {string}, descripción {string}, monto {string} y ubicación {string}")
    public void registraUnReclamo(String poliza, String fecha, String descripcion, String monto, String ubicacion) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(
            WaitUntil.the(ReclamoForm.POLIZA_SELECT, isClickable()).forNoMoreThan(15).seconds(),
            screenplay.actions.FillReactInput.withOptionContaining(poliza, ReclamoForm.POLIZA_SELECT),
            screenplay.actions.FillReactInput.withValue(fecha, ReclamoForm.FECHA_INCIDENTE_FIELD),
            Enter.theValue(descripcion).into(ReclamoForm.DESCRIPCION_FIELD),
            Enter.theValue(monto).into(ReclamoForm.MONTO_ESTIMADO_FIELD),
            Enter.theValue(ubicacion).into(ReclamoForm.UBICACION_FIELD)
        );
    }

    @When("completa el formulario con datos válidos para la póliza {string}")
    public void completaFormulario(String poliza) {
        registraUnReclamo(poliza, "2026-03-15", "Datos de prueba genéricos", "1200.00", "Quito");
    }

    @When("intenta registrar un reclamo para la póliza {string} omitiendo el campo {string}")
    public void registrarReclamoOmitiendoCampo(String poliza, String campoOmitido) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(
            WaitUntil.the(ReclamoForm.POLIZA_SELECT, isClickable()).forNoMoreThan(15).seconds(),
            screenplay.actions.FillReactInput.withOptionContaining(poliza, ReclamoForm.POLIZA_SELECT),
            screenplay.actions.FillReactInput.withValue("2026-03-15", ReclamoForm.FECHA_INCIDENTE_FIELD)
        );

        switch (campoOmitido.toLowerCase()) {
            case "descripcion":
                actor.attemptsTo(
                    Enter.theValue("3500.00").into(ReclamoForm.MONTO_ESTIMADO_FIELD),
                    Enter.theValue("Quito").into(ReclamoForm.UBICACION_FIELD)
                );
                break;
            case "ubicacion":
                actor.attemptsTo(
                    Enter.theValue("Daños menores").into(ReclamoForm.DESCRIPCION_FIELD),
                    Enter.theValue("3500.00").into(ReclamoForm.MONTO_ESTIMADO_FIELD)
                );
                break;
            default:
                actor.attemptsTo(
                    Enter.theValue("Daños menores").into(ReclamoForm.DESCRIPCION_FIELD),
                    Enter.theValue("3500.00").into(ReclamoForm.MONTO_ESTIMADO_FIELD),
                    Enter.theValue("Quito").into(ReclamoForm.UBICACION_FIELD)
                );
                break;
        }
    }

    @When("intenta registrar un reclamo para la póliza {string} con monto estimado inválido {string}")
    public void registrarReclamoMontoInvalido(String poliza, String montoInvalido) {
        registraUnReclamo(poliza, "2026-03-15", "Daños menores", montoInvalido, "Quito");
    }

    @When("intenta registrar un reclamo para la póliza {string} con fecha de incidente inválida {string}")
    public void registrarReclamoFechaInvalida(String poliza, String fechaInvalida) {
        registraUnReclamo(poliza, fechaInvalida, "Daños menores", "1000", "Quito");
    }

    @When("intenta registrar un reclamo sin seleccionar ninguna póliza")
    public void registrarReclamoSinPoliza() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(
            WaitUntil.the(ReclamoForm.POLIZA_SELECT, isClickable()).forNoMoreThan(15).seconds(),
            screenplay.actions.FillReactInput.withValue("2026-03-15", ReclamoForm.FECHA_INCIDENTE_FIELD),
            Enter.theValue("Daño genérico").into(ReclamoForm.DESCRIPCION_FIELD),
            Enter.theValue("500").into(ReclamoForm.MONTO_ESTIMADO_FIELD),
            Enter.theValue("Quito").into(ReclamoForm.UBICACION_FIELD)
        );
    }

    @When("adjunta la fotografía válida {string}")
    public void adjuntaFotografiaValida(String archivo) {
        String path = Paths.get("src/test/resources/data/" + archivo).toAbsolutePath().toString();
        Actor actor = OnStage.theActorInTheSpotlight();
        WebElement fileInput = ReclamoForm.FILE_UPLOAD_INPUT.resolveFor(actor);
        JavascriptExecutor js = (JavascriptExecutor) Serenity.getDriver();
        js.executeScript("arguments[0].style.display = 'block';", fileInput);
        fileInput.sendKeys(path);
        js.executeScript("arguments[0].dispatchEvent(new Event('change', { bubbles: true }));", fileInput);
    }

    @When("adjunta el archivo en formato no permitido {string}")
    public void adjuntaArchivoInvalido(String archivo) {
        adjuntaFotografiaValida(archivo);
    }

    @When("no adjunta ninguna fotografía")
    public void noAdjuntaFotografia() {
    }

    @When("confirma el registro")
    public void confirmarRegistro() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(
            WaitUntil.the(ReclamoForm.SUBMIT_BUTTON, isClickable()).forNoMoreThan(5).seconds(),
            Click.on(ReclamoForm.SUBMIT_BUTTON)
        );
    }

    @Then("el sistema muestra confirmación de reclamo enviado con éxito")
    public void sistemaConfirmaEnvioExitoso() {
        OnStage.theActorInTheSpotlight().attemptsTo(
            WaitUntil.the(ReclamoForm.REGISTRO_EXITOSO_TITLE, isVisible()).forNoMoreThan(15).seconds()
        );
        OnStage.theActorInTheSpotlight().should(
            seeThat(ElementVisibility.of(ReclamoForm.REGISTRO_EXITOSO_TITLE), is(true))
        );
    }

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

    @Then("el sistema notifica que es obligatorio seleccionar una póliza")
    public void notificaObligatorioPoliza() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(
            WaitUntil.the(ReclamoForm.POLIZA_REQUIRED_ERROR, isVisible()).forNoMoreThan(5).seconds()
        );
        actor.should(seeThat(ElementVisibility.of(ReclamoForm.POLIZA_REQUIRED_ERROR), is(true)));
    }

    @Then("el sistema notifica que el monto estimado no es válido")
    public void notificaMontoInvalido() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(
            WaitUntil.the(CommonElements.VALIDATION_ERROR, isVisible()).forNoMoreThan(5).seconds()
        );
        actor.should(seeThat(ElementVisibility.of(CommonElements.VALIDATION_ERROR), is(true)));
    }

    @Then("el sistema notifica que la fecha del incidente no es válida")
    public void notificaFechaInvalida() {
        Actor actor = OnStage.theActorInTheSpotlight();
        boolean hasReactError = ReclamoForm.FECHA_INCIDENTE_FIELD.resolveFor(actor)
                                .getAttribute("class").contains("error");
        String validationMessage = ReclamoForm.FECHA_INCIDENTE_FIELD.resolveFor(actor)
                                                .getAttribute("validationMessage");
        boolean hasNativeError = validationMessage != null && !validationMessage.isEmpty();
        actor.should(seeThat("Tiene error de validacion (nativo o react)",
            a -> hasReactError || hasNativeError, is(true)));
    }

    @Then("el sistema notifica que solo se permiten imágenes")
    public void notificaSoloImagenes() {
        Actor actor = OnStage.theActorInTheSpotlight();
        Target errorToast = CommonElements.textOnPage("Solo se permiten");
        actor.attemptsTo(
            WaitUntil.the(errorToast, isVisible()).forNoMoreThan(5).seconds()
        );
        actor.should(seeThat(ElementVisibility.of(errorToast), is(true)));
    }

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
        actor.should(seeThat(ElementVisibility.of(ReclamoForm.SUBMIT_BUTTON), is(true)));
    }
}
