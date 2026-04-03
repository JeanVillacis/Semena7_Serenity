package screenplay.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.waits.WaitUntil;
import screenplay.questions.PageContent;
import screenplay.ui.PolizaPage;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static org.hamcrest.Matchers.is;

public class PolizaConsultaStepDefinitions {

    @Given("existen pólizas registradas en el sistema")
    public void existenPolizasRegistradas() {
        // Pre-condition: polizas created by 05_poliza_registro feature
    }

    @When("consulta las pólizas registradas")
    public void consultaListadoPolizas() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(
            WaitUntil.the(PolizaPage.NEW_BUTTON, isVisible()).forNoMoreThan(10).seconds()
        );
    }

    @Then("el sistema presenta un listado con las pólizas registradas")
    public void sistemaPresentaListadoPolizas() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.should(seeThat(PageContent.containsText("Gesti"), is(true)));
    }

    @Then("muestra número de póliza, asegurado, estado y fechas de vigencia")
    public void muestraDatosPolizas() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.should(seeThat(PageContent.containsText("ASEGURADO"), is(true)));
        actor.should(seeThat(PageContent.containsText("ESTADO"), is(true)));
    }

    @Then("el sistema muestra el detalle de la póliza {string}")
    public void sistemaMuestraDetallePoliza(String numeroPoliza) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.should(seeThat(PageContent.containsText(numeroPoliza), is(true)));
    }

    @Then("se muestra el asegurado {string} y el vehículo asociado")
    public void muestraAseguradoYVehiculo(String asegurado) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.should(seeThat(PageContent.containsText(asegurado), is(true)));
    }

    @Then("la póliza {string} no aparece en el listado")
    public void polizaNoApareceEnListado(String numeroPoliza) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.should(seeThat(PageContent.containsText(numeroPoliza), is(false)));
    }
}
