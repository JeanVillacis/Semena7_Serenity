package screenplay.ui;

import net.serenitybdd.screenplay.targets.Target;

public class EstadoReclamoPage {

    public static final Target SEARCH_INPUT = Target.the("campo número de seguimiento")
        .locatedBy("//input[contains(@placeholder,'REC-')]");

    public static final Target SEARCH_BUTTON = Target.the("botón Consultar")
        .locatedBy("//button[contains(text(),'Consultar')]");

    public static final Target ESTADO_BADGE = Target.the("badge de estado del reclamo")
        .locatedBy("//span[contains(@class,'rounded-full') and contains(@class,'font-bold')]");

    public static final Target MONTO_APROBADO = Target.the("monto aprobado")
        .locatedBy("//span[contains(@class,'text-emerald-600') and contains(@class,'font-bold')]");

    public static final Target DEDUCIBLE_APLICADO = Target.the("deducible aplicado")
        .locatedBy("//span[contains(@class,'text-red-600')]");
}
