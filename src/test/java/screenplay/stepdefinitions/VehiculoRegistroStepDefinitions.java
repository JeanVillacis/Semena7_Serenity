package screenplay.stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.waits.WaitUntil;
import screenplay.model.VehiculoData;
import screenplay.questions.ElementVisibility;
import screenplay.questions.PageContent;
import screenplay.tasks.RegisterVehiculo;
import screenplay.ui.CommonElements;
import screenplay.ui.VehiculoForm;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isNotVisible;
import static org.hamcrest.Matchers.is;

public class VehiculoRegistroStepDefinitions {

    @When("registra un vehículo con marca {string}, modelo {string}, año {string} y placa {string}")
    public void registraVehiculo(String marca, String modelo, String anio, String placa) {
        VehiculoData data = VehiculoData.builder()
            .marca(marca).modelo(modelo).anio(anio).placa(placa).build();
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(RegisterVehiculo.withData(data));
    }

    @When("intenta registrar un vehículo con marca {string}, modelo {string}, año {string} y placa {string}")
    public void intentaRegistrarVehiculo(String marca, String modelo, String anio, String placa) {
        VehiculoData data = VehiculoData.builder()
            .marca(marca).modelo(modelo).anio(anio).placa(placa).build();
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(RegisterVehiculo.withData(data));
    }

    @Then("el sistema registra el vehículo exitosamente")
    public void vehiculoRegistradoExitosamente() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(
            WaitUntil.the(VehiculoForm.MODAL_TITLE, isNotVisible()).forNoMoreThan(10).seconds()
        );
    }

    @Then("el vehículo {string} con placa {string} queda registrado")
    public void vehiculoQuedaRegistrado(String descripcion, String placa) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.should(seeThat(PageContent.containsText(placa), is(true)));
    }

    @Then("el sistema notifica que el campo {string} del vehículo es obligatorio")
    public void notificaCampoVehiculoObligatorio(String campo) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.should(seeThat(
            ElementVisibility.of(CommonElements.VALIDATION_ERROR), is(true)
        ));
    }

    @Then("no se crea ningún registro de vehículo")
    public void noSeCreaNingunRegistroDeVehiculo() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.should(seeThat(
            ElementVisibility.of(VehiculoForm.MODAL_TITLE), is(true)
        ));
    }

    @Then("el sistema notifica que ya existe un vehículo con esa placa")
    public void notificaPlacaDuplicada() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.should(seeThat(
            ElementVisibility.of(VehiculoForm.MODAL_TITLE), is(true)
        ));
    }

    @Then("el sistema notifica que el campo año tiene formato inválido")
    public void notificaAnioInvalido() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.should(seeThat(
            ElementVisibility.of(CommonElements.VALIDATION_ERROR), is(true)
        ));
    }
}
