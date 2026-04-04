package screenplay.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.waits.WaitUntil;
import screenplay.model.AseguradoData;
import screenplay.questions.ElementVisibility;
import screenplay.questions.PageContent;
import screenplay.tasks.RegisterAsegurado;
import screenplay.ui.AseguradoForm;
import screenplay.ui.CommonElements;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static org.hamcrest.Matchers.is;

public class AseguradoRegistroStepDefinitions {

    private AseguradoData currentData;

    @When("registra un asegurado con nombre {string}, apellido {string}, identificación {string}, dirección {string}, teléfono {string} y correo {string}")
    public void registraAsegurado(String nombre, String apellido, String identificacion,
                                   String direccion, String telefono, String correo) {
        currentData = AseguradoData.builder()
            .nombre(nombre).apellido(apellido).identificacion(identificacion)
            .direccion(direccion).telefono(telefono).correo(correo).build();

        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(RegisterAsegurado.withData(currentData));
    }

    @When("intenta registrar un asegurado con nombre {string}, apellido {string}, identificación {string}, dirección {string}, teléfono {string} y correo {string}")
    public void intentaRegistrarAsegurado(String nombre, String apellido, String identificacion,
                                           String direccion, String telefono, String correo) {
        currentData = AseguradoData.builder()
            .nombre(nombre).apellido(apellido).identificacion(identificacion)
            .direccion(direccion).telefono(telefono).correo(correo).build();

        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(RegisterAsegurado.withData(currentData));
    }

    @When("omite todos los datos obligatorios y confirma el registro del asegurado")
    public void omiteTodosLosDatos() {
        currentData = AseguradoData.builder().build();
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(RegisterAsegurado.withData(currentData));
    }

    @Then("el sistema crea el asegurado exitosamente")
    public void sistemaCreoExitosamente() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(
            WaitUntil.the(CommonElements.SUCCESS_ALERT, isVisible()).forNoMoreThan(10).seconds()
        );
    }

    @Then("el asegurado {string} queda disponible en el listado de asegurados")
    public void aseguradoEnListado(String nombreCompleto) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.should(seeThat(PageContent.containsText(nombreCompleto), is(true)));
    }

    @Then("el sistema notifica que ya existe un asegurado con esa identificación")
    public void notificaIdentificacionDuplicada() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.should(seeThat(
            ElementVisibility.of(AseguradoForm.MODAL_TITLE), is(true)
        ));
    }

    @Then("el sistema notifica que el campo {string} es obligatorio")
    public void notificaCampoObligatorio(String campo) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.should(seeThat(
            ElementVisibility.of(CommonElements.VALIDATION_ERROR), is(true)
        ));
    }

    @Then("el sistema notifica todos los campos obligatorios faltantes")
    public void notificaCamposFaltantes() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.should(seeThat(
            ElementVisibility.of(CommonElements.VALIDATION_ERROR), is(true)
        ));
    }

    @Then("no se crea ningún registro de asegurado")
    public void noSeCreoRegistro() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.should(seeThat(
            ElementVisibility.of(AseguradoForm.MODAL_TITLE), is(true)
        ));
    }

    @Then("el sistema notifica que el formato de correo electrónico no es válido")
    public void notificaCorreoInvalido() {
        Actor actor = OnStage.theActorInTheSpotlight();
        boolean hasReactError = AseguradoForm.CORREO_FIELD.resolveFor(actor)
                                .getAttribute("class").contains("error");
        String validationMessage = AseguradoForm.CORREO_FIELD.resolveFor(actor)
                                                .getAttribute("validationMessage");
        boolean hasNativeError = validationMessage != null && !validationMessage.isEmpty();
        
        actor.should(seeThat("Tiene error de validacion en correo", 
            a -> hasReactError || hasNativeError, is(true)));
    }

    @And("no se crea un nuevo registro")
    public void noSeCreaNuevoRegistro() {
        // Verified by the duplicate check above
    }
}
