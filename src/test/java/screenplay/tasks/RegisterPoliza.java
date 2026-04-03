package screenplay.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.annotations.Step;
import screenplay.model.PolizaData;
import screenplay.ui.PolizaForm;
import screenplay.ui.PolizaPage;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class RegisterPoliza implements Task {

    private final PolizaData data;

    public RegisterPoliza(PolizaData data) {
        this.data = data;
    }

    public static RegisterPoliza withData(PolizaData data) {
        return new RegisterPoliza(data);
    }

    @Override
    @Step("{0} registra una póliza con número #data.numeroPoliza")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            Click.on(PolizaPage.NEW_BUTTON),
            WaitUntil.the(PolizaForm.MODAL_TITLE, isVisible()).forNoMoreThan(10).seconds()
        );

        if (!data.getNumeroPoliza().isEmpty()) {
            actor.attemptsTo(Enter.theValue(data.getNumeroPoliza()).into(PolizaForm.NUMERO_POLIZA_FIELD));
        }
        if (!data.getAsegurado().isEmpty()) {
            actor.attemptsTo(SelectFromOptions.byVisibleText(data.getAsegurado()).from(PolizaForm.ASEGURADO_SELECT));
        }
        if (!data.getVehiculo().isEmpty()) {
            actor.attemptsTo(SelectFromOptions.byVisibleText(data.getVehiculo()).from(PolizaForm.VEHICULO_SELECT));
        }
        if (!data.getValorAsegurado().isEmpty()) {
            actor.attemptsTo(Enter.theValue(data.getValorAsegurado()).into(PolizaForm.VALOR_ASEGURADO_FIELD));
        }
        if (!data.getVigenciaInicio().isEmpty()) {
            actor.attemptsTo(Enter.theValue(data.getVigenciaInicio()).into(PolizaForm.VIGENCIA_INICIO_FIELD));
        }
        if (!data.getVigenciaFin().isEmpty()) {
            actor.attemptsTo(Enter.theValue(data.getVigenciaFin()).into(PolizaForm.VIGENCIA_FIN_FIELD));
        }

        actor.attemptsTo(Click.on(PolizaForm.SUBMIT_BUTTON));
    }
}
