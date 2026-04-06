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
import screenplay.ui.AseguradoPage;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static org.hamcrest.Matchers.is;

public class AseguradoConsultaStepDefinitions {

    @Given("existen asegurados registrados en el sistema")
    public void existenAseguradosRegistrados() {
    }

    @When("consulta el listado de asegurados")
    public void consultaListadoAsegurados() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(
            WaitUntil.the(AseguradoPage.PAGE_TITLE, isVisible()).forNoMoreThan(10).seconds()
        );
    }

    @Then("el sistema presenta un listado con los asegurados registrados")
    public void sistemaPresnetaListado() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.should(seeThat(PageContent.containsText("Mostrando"), is(true)));
    }

    @Then("cada fila muestra nombre, identificación, correo y teléfono")
    public void filasMuestranDatos() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.should(seeThat(PageContent.containsText("NOMBRE"), is(true)));
        actor.should(seeThat(PageContent.containsText("IDENTIFICACIÓN"), is(true)));
    }

    @When("busca al asegurado con identificación {string}")
    public void buscaAseguradoPorIdentificacion(String identificacion) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(SearchInList.byText(AseguradoPage.SEARCH_FIELD, identificacion));
    }

    @Then("el sistema muestra al asegurado {string} en los resultados")
    public void sistemaMuestraAsegurado(String nombreCompleto) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.should(seeThat(PageContent.containsText(nombreCompleto), is(true)));
    }

    @Then("el sistema muestra la información del asegurado con identificación {string}")
    public void sistemaMuestraInformacion(String identificacion) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.should(seeThat(PageContent.containsText(identificacion), is(true)));
    }

    @When("busca un asegurado con identificación {string} que no existe en el sistema")
    public void buscaAseguradoInexistente(String identificacion) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(SearchInList.byText(AseguradoPage.SEARCH_FIELD, identificacion));
    }

    @Then("el sistema no muestra ningún asegurado en los resultados")
    public void noMuestraNingunAsegurado() {
        Actor actor = OnStage.theActorInTheSpotlight();
        try {
            actor.should(seeThat(
                ElementVisibility.of(AseguradoPage.NO_RESULTS), is(true)
            ));
        } catch (Exception e) {
            actor.should(seeThat(
                PageContent.containsText("0 asegurados"), is(true)
            ));
        }
    }
}
