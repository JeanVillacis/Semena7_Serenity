package screenplay.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.waits.WaitUntil;
import screenplay.questions.ElementVisibility;
import screenplay.questions.PageContent;
import screenplay.ui.EstadoReclamoPage;
import screenplay.ui.ReclamoForm;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static org.hamcrest.Matchers.is;

public class ReclamoConsultaEstadoStepDefinitions {

    /**
     * Lee el número de seguimiento de la pantalla de éxito (tras confirmar el registro)
     * y lo guarda en la memoria del actor para usarlo en la consulta de estado.
     */
    @And("el actor recuerda el número de seguimiento del reclamo")
    public void actorRecuerdaNumeroSeguimiento() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(
            WaitUntil.the(ReclamoForm.TRACKING_ID_LABEL, isVisible()).forNoMoreThan(15).seconds()
        );
        String trackingNumber = ReclamoForm.TRACKING_ID_LABEL.resolveFor(actor).getText().trim();
        actor.remember("trackingNumber", trackingNumber);
    }

    /**
     * Navega directamente a /estado-reclamo y espera que el campo de búsqueda esté listo.
     */
    @And("navega a la consulta de estado del reclamo")
    public void navegaConsultaEstado() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(
            Open.url("http://localhost:4000/estado-reclamo"),
            WaitUntil.the(EstadoReclamoPage.SEARCH_INPUT, isVisible()).forNoMoreThan(10).seconds()
        );
    }

    /**
     * Ingresa el número de seguimiento recordado y ejecuta la consulta.
     */
    @When("consulta el estado del reclamo registrado")
    public void consultaEstadoReclamoRegistrado() {
        Actor actor = OnStage.theActorInTheSpotlight();
        String trackingNumber = actor.recall("trackingNumber");
        actor.attemptsTo(
            Enter.theValue(trackingNumber).into(EstadoReclamoPage.SEARCH_INPUT),
            Click.on(EstadoReclamoPage.SEARCH_BUTTON),
            WaitUntil.the(EstadoReclamoPage.ESTADO_BADGE, isVisible()).forNoMoreThan(10).seconds()
        );
    }

    /**
     * Verifica que el texto del estado esperado aparece en la página.
     * La UI transforma EN_REVISION_MANUAL → "EN REVISION MANUAL" (replace _ con espacio).
     */
    @Then("el sistema muestra el estado {string}")
    public void sistemaMuestraEstado(String estadoEsperado) {
        OnStage.theActorInTheSpotlight().should(
            seeThat(PageContent.containsText(estadoEsperado), is(true))
        );
    }

    /**
     * CP001 – Verifica que el detalle financiero muestra el monto aprobado (verde)
     * y el deducible aplicado (rojo).
     */
    @Then("se muestra el monto aprobado y el deducible aplicado")
    public void muestraMontoAprobadoYDeducible() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.should(seeThat(ElementVisibility.of(EstadoReclamoPage.MONTO_APROBADO), is(true)));
        actor.should(seeThat(ElementVisibility.of(EstadoReclamoPage.DEDUCIBLE_APLICADO), is(true)));
    }

    /**
     * CP002 – Verifica que el mensaje de revisión manual está en pantalla.
     * El badge ya fue verificado por el step anterior; aquí confirmamos el texto.
     */
    @Then("se muestra el mensaje de revisión manual")
    public void muestraMensajeRevisionManual() {
        OnStage.theActorInTheSpotlight().should(
            seeThat(PageContent.containsText("REVISION MANUAL"), is(true))
        );
    }

    /**
     * CP003 – Verifica que el estado DESCARTADO está visible en la página.
     */
    @Then("se muestra el motivo del descarte")
    public void muestraMotivDelDescarte() {
        OnStage.theActorInTheSpotlight().should(
            seeThat(PageContent.containsText("DESCARTADO"), is(true))
        );
    }
}
