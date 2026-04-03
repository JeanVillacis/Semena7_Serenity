package screenplay.tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import screenplay.ui.AseguradoPage;
import screenplay.ui.PolizaPage;
import screenplay.ui.SidebarMenu;
import screenplay.ui.VehiculoPage;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class NavigateTo {

    public static Performable asegurados() {
        return Task.where("{0} navega a la sección de Asegurados",
            Click.on(SidebarMenu.ASEGURADOS_LINK),
            WaitUntil.the(AseguradoPage.PAGE_TITLE, isVisible()).forNoMoreThan(10).seconds()
        );
    }

    public static Performable vehiculos() {
        return Task.where("{0} navega a la sección de Vehículos",
            Click.on(SidebarMenu.VEHICULOS_LINK),
            WaitUntil.the(VehiculoPage.PAGE_TITLE, isVisible()).forNoMoreThan(10).seconds()
        );
    }

    public static Performable polizas() {
        return Task.where("{0} navega a la sección de Pólizas",
            Click.on(SidebarMenu.POLIZAS_LINK),
            WaitUntil.the(PolizaPage.NEW_BUTTON, isVisible()).forNoMoreThan(10).seconds()
        );
    }
}
