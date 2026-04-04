package screenplay.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.annotations.Step;
import screenplay.actions.FillReactInput;
import screenplay.model.VehiculoData;
import screenplay.ui.VehiculoForm;
import screenplay.ui.VehiculoPage;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

public class RegisterVehiculo implements Task {

    private final VehiculoData data;

    public RegisterVehiculo(VehiculoData data) {
        this.data = data;
    }

    public static RegisterVehiculo withData(VehiculoData data) {
        return new RegisterVehiculo(data);
    }

    @Override
    @Step("{0} registra un vehículo con placa #data.placa")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            WaitUntil.the(VehiculoPage.NEW_BUTTON, isClickable()).forNoMoreThan(10).seconds(),
            Click.on(VehiculoPage.NEW_BUTTON),
            WaitUntil.the(VehiculoForm.MODAL_TITLE, isVisible()).forNoMoreThan(15).seconds()
        );

        if (!data.getMarca().isEmpty()) {
            actor.attemptsTo(Enter.theValue(data.getMarca()).into(VehiculoForm.MARCA_FIELD));
        }
        if (!data.getModelo().isEmpty()) {
            actor.attemptsTo(Enter.theValue(data.getModelo()).into(VehiculoForm.MODELO_FIELD));
        }
        if (!data.getAnio().isEmpty()) {
            actor.attemptsTo(FillReactInput.withValue(data.getAnio(), VehiculoForm.ANIO_FIELD));
        }
        if (!data.getPlaca().isEmpty()) {
            actor.attemptsTo(Enter.theValue(data.getPlaca()).into(VehiculoForm.PLACA_FIELD));
        }

        actor.attemptsTo(
            WaitUntil.the(VehiculoForm.SUBMIT_BUTTON, isClickable()).forNoMoreThan(5).seconds(),
            Click.on(VehiculoForm.SUBMIT_BUTTON)
        );
    }
}
