package screenplay.stepdefinitions;

import io.cucumber.java.en.Given;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import screenplay.tasks.Login;
import screenplay.tasks.NavigateTo;

public class LoginStepDefinitions {

    @Given("que el gestor está autenticado en el sistema")
    public void gestorAutenticado() {
        Actor actor = OnStage.theActorCalled("gestor");
        actor.attemptsTo(Login.withCredentials("gestor01", "gestor123"));
    }

    @Given("accede al módulo de asegurados")
    public void accedeAsegurados() {
        OnStage.theActorInTheSpotlight().attemptsTo(NavigateTo.asegurados());
    }

    @Given("accede al módulo de vehículos")
    public void accedeVehiculos() {
        OnStage.theActorInTheSpotlight().attemptsTo(NavigateTo.vehiculos());
    }

    @Given("accede al módulo de pólizas")
    public void accedePolizas() {
        OnStage.theActorInTheSpotlight().attemptsTo(NavigateTo.polizas());
    }
}
