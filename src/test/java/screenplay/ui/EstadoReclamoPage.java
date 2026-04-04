package screenplay.ui;

import net.serenitybdd.screenplay.targets.Target;

public class EstadoReclamoPage {

    /** Campo de búsqueda por número de seguimiento (placeholder "REC-YYYY-NNN"). */
    public static final Target SEARCH_INPUT = Target.the("campo número de seguimiento")
        .locatedBy("//input[contains(@placeholder,'REC-')]");

    /** Botón Consultar que dispara la búsqueda. */
    public static final Target SEARCH_BUTTON = Target.the("botón Consultar")
        .locatedBy("//button[contains(text(),'Consultar')]");

    /**
     * Badge de estado del reclamo.
     * Badge renderiza: <span class="inline-flex ... rounded-full ... font-medium {colorClass} text-sm font-bold px-3 py-1">
     * Texto puede ser: APROBADO | DESCARTADO | EN REVISION MANUAL | REGISTRADO | RECHAZADO
     */
    public static final Target ESTADO_BADGE = Target.the("badge de estado del reclamo")
        .locatedBy("//span[contains(@class,'rounded-full') and contains(@class,'font-bold')]");

    /**
     * Monto aprobado en el detalle financiero (texto en verde emerald).
     * Visible solo cuando estado = APROBADO.
     */
    public static final Target MONTO_APROBADO = Target.the("monto aprobado")
        .locatedBy("//span[contains(@class,'text-emerald-600') and contains(@class,'font-bold')]");

    /**
     * Deducible aplicado en el detalle financiero (texto en rojo).
     * Visible cuando el deducible fue calculado (APROBADO o DESCARTADO).
     */
    public static final Target DEDUCIBLE_APLICADO = Target.the("deducible aplicado")
        .locatedBy("//span[contains(@class,'text-red-600')]");
}
