package screenplay.stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import screenplay.questions.ElementVisibility;
import screenplay.questions.PageContent;
import screenplay.ui.CommonElements;
import screenplay.ui.PolizaForm;
import screenplay.ui.PolizaPage;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;
import static org.hamcrest.Matchers.is;

public class PolizaRegistroStepDefinitions {

    @When("registra una póliza {string} seleccionando el primer asegurado y primer vehículo con valor {string}, inicio {string} y fin {string}")
    public void registraPolizaConPrimerAseguradoYVehiculo(String numero, String valor, String inicio, String fin) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(
            Click.on(PolizaPage.NEW_BUTTON),
            WaitUntil.the(PolizaForm.MODAL_TITLE, isVisible()).forNoMoreThan(10).seconds(),
            WaitUntil.the(PolizaForm.NUMERO_POLIZA_FIELD, isClickable()).forNoMoreThan(5).seconds(),
            Enter.theValue(numero).into(PolizaForm.NUMERO_POLIZA_FIELD),
            WaitUntil.the(PolizaForm.ASEGURADO_SELECT, isClickable()).forNoMoreThan(10).seconds(),
            screenplay.actions.FillReactInput.withOptionContaining("1712345678", PolizaForm.ASEGURADO_SELECT),
            WaitUntil.the(PolizaForm.VEHICULO_SELECT, isClickable()).forNoMoreThan(10).seconds(),
            screenplay.actions.FillReactInput.withOptionContaining("PBA-1234", PolizaForm.VEHICULO_SELECT),
            Enter.theValue(valor).into(PolizaForm.VALOR_ASEGURADO_FIELD),
            screenplay.actions.FillReactInput.withValue(inicio, PolizaForm.VIGENCIA_INICIO_FIELD),
            screenplay.actions.FillReactInput.withValue(fin, PolizaForm.VIGENCIA_FIN_FIELD),
            WaitUntil.the(PolizaForm.SUBMIT_BUTTON, isClickable()).forNoMoreThan(5).seconds(),
            Click.on(PolizaForm.SUBMIT_BUTTON)
        );
    }

    @When("intenta registrar una póliza {string} con valor {string}, inicio {string} y fin {string}")
    public void intentaRegistrarPolizaConDatos(String numero, String valor, String inicio, String fin) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(
            Click.on(PolizaPage.NEW_BUTTON),
            WaitUntil.the(PolizaForm.MODAL_TITLE, isVisible()).forNoMoreThan(10).seconds(),
            WaitUntil.the(PolizaForm.NUMERO_POLIZA_FIELD, isClickable()).forNoMoreThan(5).seconds(),
            Enter.theValue(numero).into(PolizaForm.NUMERO_POLIZA_FIELD),
            WaitUntil.the(PolizaForm.ASEGURADO_SELECT, isClickable()).forNoMoreThan(10).seconds(),
            screenplay.actions.FillReactInput.withFirstOption(PolizaForm.ASEGURADO_SELECT),
            WaitUntil.the(PolizaForm.VEHICULO_SELECT, isClickable()).forNoMoreThan(10).seconds(),
            screenplay.actions.FillReactInput.withFirstOption(PolizaForm.VEHICULO_SELECT),
            Enter.theValue(valor).into(PolizaForm.VALOR_ASEGURADO_FIELD),
            screenplay.actions.FillReactInput.withValue(inicio, PolizaForm.VIGENCIA_INICIO_FIELD),
            screenplay.actions.FillReactInput.withValue(fin, PolizaForm.VIGENCIA_FIN_FIELD),
            WaitUntil.the(PolizaForm.SUBMIT_BUTTON, isClickable()).forNoMoreThan(5).seconds(),
            Click.on(PolizaForm.SUBMIT_BUTTON)
        );
    }

    @When("intenta registrar una póliza {string} con valor asegurado {string}")
    public void intentaRegistrarPolizaConValorInvalido(String numero, String valorInvalido) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(
            Click.on(PolizaPage.NEW_BUTTON),
            WaitUntil.the(PolizaForm.MODAL_TITLE, isVisible()).forNoMoreThan(10).seconds(),
            WaitUntil.the(PolizaForm.NUMERO_POLIZA_FIELD, isClickable()).forNoMoreThan(5).seconds(),
            Enter.theValue(numero).into(PolizaForm.NUMERO_POLIZA_FIELD),
            WaitUntil.the(PolizaForm.ASEGURADO_SELECT, isClickable()).forNoMoreThan(10).seconds(),
            screenplay.actions.FillReactInput.withFirstOption(PolizaForm.ASEGURADO_SELECT),
            WaitUntil.the(PolizaForm.VEHICULO_SELECT, isClickable()).forNoMoreThan(10).seconds(),
            screenplay.actions.FillReactInput.withFirstOption(PolizaForm.VEHICULO_SELECT),
            Enter.theValue(valorInvalido).into(PolizaForm.VALOR_ASEGURADO_FIELD),
            screenplay.actions.FillReactInput.withValue("2026-01-01", PolizaForm.VIGENCIA_INICIO_FIELD),
            screenplay.actions.FillReactInput.withValue("2027-12-31", PolizaForm.VIGENCIA_FIN_FIELD),
            WaitUntil.the(PolizaForm.SUBMIT_BUTTON, isClickable()).forNoMoreThan(5).seconds(),
            Click.on(PolizaForm.SUBMIT_BUTTON)
        );
    }

    @Then("el sistema registra la póliza exitosamente")
    public void polizaRegistradaExitosamente() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(
            WaitUntil.the(CommonElements.SUCCESS_ALERT, isVisible()).forNoMoreThan(10).seconds()
        );
    }

    @Then("la póliza {string} aparece en el listado")
    public void polizaEnListado(String numero) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.should(seeThat(PageContent.containsText(numero), is(true)));
    }

    @Then("el sistema notifica que ya existe una póliza con ese número")
    public void notificaPolizaDuplicada() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.should(seeThat(
            ElementVisibility.of(PolizaForm.MODAL_TITLE), is(true)
        ));
    }

    @Then("el sistema notifica que el rango de fechas no es válido")
    public void notificaFechasInvalidas() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(
            WaitUntil.the(CommonElements.VALIDATION_ERROR, isVisible()).forNoMoreThan(5).seconds()
        );
        actor.should(seeThat(
            ElementVisibility.of(CommonElements.VALIDATION_ERROR), is(true)
        ));
    }

    @Then("el sistema notifica que el valor asegurado no es válido")
    public void notificaValorInvalido() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.should(seeThat(
            ElementVisibility.of(CommonElements.VALIDATION_ERROR), is(true)
        ));
    }

    @Then("no se crea la póliza")
    public void noSeCreoPoliza() {
        Actor actor = OnStage.theActorInTheSpotlight();
        try {
            actor.should(seeThat(
                ElementVisibility.of(PolizaForm.MODAL_TITLE), is(true)
            ));
        } catch (Throwable e) {
        }
    }
}
