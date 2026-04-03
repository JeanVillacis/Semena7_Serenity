package screenplay.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.waits.WaitUntil;
import screenplay.questions.ElementVisibility;
import screenplay.questions.PageContent;
import screenplay.tasks.SearchInList;
import screenplay.ui.VehiculoPage;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static org.hamcrest.Matchers.is;

public class VehiculoConsultaStepDefinitions {

    @Given("existen vehículos registrados en el sistema")
    public void existenVehiculosRegistrados() {
        // Pre-condition: vehicles created by 03_vehiculo_registro feature
    }

    @When("consulta el listado de vehículos")
    public void consultaListadoVehiculos() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(
            WaitUntil.the(VehiculoPage.NEW_BUTTON, isVisible()).forNoMoreThan(10).seconds()
        );
    }

    @Then("el sistema presenta un listado con los vehículos registrados")
    public void sistemaPresnetaListadoVehiculos() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.should(seeThat(PageContent.containsText("Listado Maestro"), is(true)));
    }

    @Then("cada fila muestra marca, modelo, año y placa")
    public void filasMuestranDatosVehiculos() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.should(seeThat(PageContent.containsText("PLACA"), is(true)));
    }

    @When("busca el vehículo con placa {string}")
    public void buscaVehiculoPorPlaca(String placa) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(SearchInList.byText(VehiculoPage.SEARCH_FIELD, placa));
    }

    @Then("el sistema muestra el vehículo {string} con placa {string}")
    public void sistemaMuestraVehiculo(String descripcion, String placa) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.should(seeThat(PageContent.containsText(placa), is(true)));
    }

    @When("busca un vehículo con placa {string} que no existe en el sistema")
    public void buscaVehiculoInexistente(String placa) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(SearchInList.byText(VehiculoPage.SEARCH_FIELD, placa));
    }

    @Then("el sistema no muestra ningún vehículo en los resultados")
    public void noMuestraNingunVehiculo() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.should(seeThat(
            ElementVisibility.of(VehiculoPage.NO_RESULTS), is(true)
        ));
    }
}
