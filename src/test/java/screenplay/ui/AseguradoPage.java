package screenplay.ui;

import net.serenitybdd.screenplay.targets.Target;

public class AseguradoPage {

    public static final Target PAGE_TITLE = Target.the("título Asegurados")
        .locatedBy("//h1[contains(text(),'Asegurados')]");

    public static final Target NEW_BUTTON = Target.the("botón Nuevo asegurado")
        .locatedBy("//button[contains(text(),'Nuevo asegurado')]");

    public static final Target SEARCH_FIELD = Target.the("campo de búsqueda")
        .locatedBy("//input[contains(@placeholder,'Buscar por nombre')]");

    public static final Target TABLE_BODY = Target.the("cuerpo de la tabla de asegurados")
        .locatedBy("//table//tbody | //div[contains(@class,'table')]");

    public static final Target TOTAL_COUNT = Target.the("total de asegurados")
        .locatedBy("//*[contains(text(),'Mostrando')]");

    public static final Target NO_RESULTS = Target.the("sin resultados")
        .locatedBy("//*[contains(text(),'No se encontr') or contains(text(),'sin resultados') or contains(text(),'Sin resultados') or contains(text(),'0 asegurados')]");

    public static Target rowWithIdentification(String identification) {
        return Target.the("fila con identificación " + identification)
            .locatedBy("//*[contains(text(),'" + identification + "')]/ancestor::tr | //*[contains(text(),'" + identification + "')]/..");
    }

    public static Target rowWithName(String name) {
        return Target.the("fila con nombre " + name)
            .locatedBy("//*[contains(text(),'" + name + "')]/ancestor::tr | //*[contains(text(),'" + name + "')]/..");
    }

    public static Target viewButton(String identification) {
        return Target.the("botón Ver para " + identification)
            .locatedBy("//*[contains(text(),'" + identification + "')]/ancestor::tr//button[contains(text(),'Ver')] | //*[contains(text(),'" + identification + "')]/..//button[contains(text(),'Ver')]");
    }
}
