package screenplay.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.annotations.Step;
import screenplay.model.AseguradoData;
import screenplay.ui.AseguradoForm;
import screenplay.ui.AseguradoPage;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class RegisterAsegurado implements Task {

    private final AseguradoData data;

    public RegisterAsegurado(AseguradoData data) {
        this.data = data;
    }

    public static RegisterAsegurado withData(AseguradoData data) {
        return new RegisterAsegurado(data);
    }

    @Override
    @Step("{0} registra un asegurado con identificación #data.identificacion")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            Click.on(AseguradoPage.NEW_BUTTON),
            WaitUntil.the(AseguradoForm.MODAL_TITLE, isVisible()).forNoMoreThan(10).seconds()
        );

        if (!data.getNombre().isEmpty()) {
            actor.attemptsTo(Enter.theValue(data.getNombre()).into(AseguradoForm.NOMBRE_FIELD));
        }
        if (!data.getApellido().isEmpty()) {
            actor.attemptsTo(Enter.theValue(data.getApellido()).into(AseguradoForm.APELLIDO_FIELD));
        }
        if (!data.getIdentificacion().isEmpty()) {
            actor.attemptsTo(Enter.theValue(data.getIdentificacion()).into(AseguradoForm.IDENTIFICACION_FIELD));
        }
        if (!data.getCorreo().isEmpty()) {
            actor.attemptsTo(Enter.theValue(data.getCorreo()).into(AseguradoForm.CORREO_FIELD));
        }
        if (!data.getTelefono().isEmpty()) {
            actor.attemptsTo(Enter.theValue(data.getTelefono()).into(AseguradoForm.TELEFONO_FIELD));
        }
        if (!data.getDireccion().isEmpty()) {
            actor.attemptsTo(Enter.theValue(data.getDireccion()).into(AseguradoForm.DIRECCION_FIELD));
        }

        actor.attemptsTo(Click.on(AseguradoForm.SUBMIT_BUTTON));
    }
}
