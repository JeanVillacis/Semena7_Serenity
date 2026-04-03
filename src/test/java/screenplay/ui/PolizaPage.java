package screenplay.ui;

import net.serenitybdd.screenplay.targets.Target;

public class PolizaPage {

    public static final Target PAGE_TITLE = Target.the("título Gestión de Pólizas")
        .locatedBy("//*[contains(text(),'liza') and (self::h1 or self::h2)]");

    public static final Target NEW_BUTTON = Target.the("botón Nueva póliza")
        .locatedBy("//button[contains(text(),'Nueva p') or contains(text(),'ueva p')]");

    public static final Target TABLE_BODY = Target.the("tabla de pólizas")
        .locatedBy("//*[contains(text(),'LIZA') and contains(@class,'uppercase')]/..");

    public static final Target TOTAL_COUNT = Target.the("total de pólizas")
        .locatedBy("//*[contains(text(),'registrada') or contains(text(),'Total')]");

    public static final Target NO_RESULTS = Target.the("sin resultados pólizas")
        .locatedBy("//*[contains(text(),'No hay p') or contains(text(),'no hay p')]");

    public static Target rowWithNumero(String numero) {
        return Target.the("fila con póliza " + numero)
            .locatedBy("//*[contains(text(),'" + numero + "')]/..");
    }
}
