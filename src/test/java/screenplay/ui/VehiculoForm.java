package screenplay.ui;

import net.serenitybdd.screenplay.targets.Target;

public class VehiculoForm {

    public static final Target MODAL_TITLE = Target.the("título modal Registrar Vehículo")
        .locatedBy("//*[contains(text(),'Registrar Veh')]");

    public static final Target MARCA_FIELD = Target.the("campo Marca")
        .locatedBy("//input[@placeholder='Chevrolet']");

    public static final Target MODELO_FIELD = Target.the("campo Modelo")
        .locatedBy("//input[@placeholder='Aveo']");

    public static final Target ANIO_FIELD = Target.the("campo Año")
        .locatedBy("//input[@placeholder='2022']");

    public static final Target PLACA_FIELD = Target.the("campo Placa")
        .locatedBy("//input[@placeholder='PBA-1234']");

    public static final Target SUBMIT_BUTTON = Target.the("botón Registrar Vehículo")
        .locatedBy("//button[contains(text(),'Registrar Veh')]");
}
