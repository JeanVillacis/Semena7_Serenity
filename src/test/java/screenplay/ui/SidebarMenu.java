package screenplay.ui;

import net.serenitybdd.screenplay.targets.Target;

public class SidebarMenu {

    public static final Target DASHBOARD_LINK = Target.the("enlace Dashboard")
        .locatedBy("//a[contains(text(),'Dashboard')]");

    public static final Target ASEGURADOS_LINK = Target.the("enlace Asegurados")
        .locatedBy("//a[contains(text(),'Asegurados')]");

    public static final Target VEHICULOS_LINK = Target.the("enlace Vehículos")
        .locatedBy("//a[contains(text(),'Veh')]");

    public static final Target POLIZAS_LINK = Target.the("enlace Pólizas")
        .locatedBy("//a[contains(text(),'liza')]");

    public static final Target RECLAMOS_LINK = Target.the("enlace Reclamos")
        .locatedBy("//a[contains(text(),'Reclamos')]");

    public static final Target LOGOUT_BUTTON = Target.the("botón cerrar sesión")
        .locatedBy("//*[contains(text(),'Cerrar sesión')]");
}
