package screenplay.ui;

import net.serenitybdd.screenplay.targets.Target;

public class VehiculoPage {

    public static final Target PAGE_TITLE = Target.the("título Vehículos")
        .locatedBy("//*[contains(text(),'culos') and (self::h1 or self::h2)]");

    public static final Target NEW_BUTTON = Target.the("botón Nuevo vehículo")
        .locatedBy("//button[contains(text(),'Nuevo veh') or contains(text(),'uevo veh')]");

    public static final Target SEARCH_FIELD = Target.the("campo de búsqueda de vehículos")
        .locatedBy("//input[contains(@placeholder,'Buscar por placa')]");

    public static final Target TABLE_BODY = Target.the("tabla de vehículos")
        .locatedBy("//*[contains(text(),'Listado Maestro')]");

    public static final Target NO_RESULTS = Target.the("sin resultados vehículos")
        .locatedBy("//*[contains(text(),'No hay veh') or contains(text(),'No se encontr') or contains(text(),'sin resultados')]");

    public static Target rowWithPlaca(String placa) {
        return Target.the("fila con placa " + placa)
            .locatedBy("//*[contains(text(),'" + placa + "')]/..");
    }
}
