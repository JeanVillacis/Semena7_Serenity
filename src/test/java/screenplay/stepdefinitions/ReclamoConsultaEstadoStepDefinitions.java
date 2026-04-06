package screenplay.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actors.OnStage;
import screenplay.config.TestConfig;
import screenplay.questions.ElementVisibility;
import screenplay.questions.PageContent;
import screenplay.ui.EstadoReclamoPage;
import screenplay.ui.ReclamoForm;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static org.hamcrest.Matchers.is;

public class ReclamoConsultaEstadoStepDefinitions {

    @And("el actor recuerda el número de seguimiento del reclamo")
    public void actorRecuerdaNumeroSeguimiento() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(
            net.serenitybdd.screenplay.waits.WaitUntil.the(ReclamoForm.TRACKING_ID_LABEL, isVisible()).forNoMoreThan(15).seconds()
        );
        String trackingNumber = ReclamoForm.TRACKING_ID_LABEL.resolveFor(actor).getText().trim();
        actor.remember("trackingNumber", trackingNumber);
    }

    @And("navega a la consulta de estado del reclamo")
    public void navegaConsultaEstado() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(
            Open.url(TestConfig.estadoReclamoUrl()),
            net.serenitybdd.screenplay.waits.WaitUntil.the(EstadoReclamoPage.SEARCH_INPUT, isVisible()).forNoMoreThan(10).seconds()
        );
    }

    @When("consulta el estado del reclamo registrado")
    public void consultaEstadoReclamoRegistrado() {
        Actor actor = OnStage.theActorInTheSpotlight();
        String trackingNumber = actor.recall("trackingNumber");
        actor.attemptsTo(
            Enter.theValue(trackingNumber).into(EstadoReclamoPage.SEARCH_INPUT),
            Click.on(EstadoReclamoPage.SEARCH_BUTTON),
            net.serenitybdd.screenplay.waits.WaitUntil.the(EstadoReclamoPage.ESTADO_BADGE, isVisible()).forNoMoreThan(10).seconds()
        );
    }

    @Then("el sistema muestra el estado {string}")
    public void sistemaMuestraEstado(String estadoEsperado) {
        OnStage.theActorInTheSpotlight().should(
            seeThat(PageContent.containsText(estadoEsperado), is(true))
        );
    }

    @Then("se muestra el monto aprobado y el deducible aplicado")
    public void muestraMontoAprobadoYDeducible() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.should(seeThat(ElementVisibility.of(EstadoReclamoPage.MONTO_APROBADO), is(true)));
        actor.should(seeThat(ElementVisibility.of(EstadoReclamoPage.DEDUCIBLE_APLICADO), is(true)));
    }

    @Then("se muestra el mensaje de revisión manual")
    public void muestraMensajeRevisionManual() {
        OnStage.theActorInTheSpotlight().should(
            seeThat(PageContent.containsText("REVISION MANUAL"), is(true))
        );
    }

    @Then("se muestra el motivo del descarte")
    public void muestraMotivDelDescarte() {
        OnStage.theActorInTheSpotlight().should(
            seeThat(PageContent.containsText("DESCARTADO"), is(true))
        );
    }
}
