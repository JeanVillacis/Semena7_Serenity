package screenplay.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.annotations.Step;
import screenplay.ui.LoginPage;
import screenplay.ui.SidebarMenu;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class Login implements Task {

    private final String username;
    private final String password;

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static Login withCredentials(String username, String password) {
        return new Login(username, password);
    }

    @Override
    @Step("{0} inicia sesión con usuario #username")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            Open.url("http://localhost:4000/login"),
            WaitUntil.the(LoginPage.USERNAME_FIELD, isVisible()).forNoMoreThan(15).seconds(),
            Enter.theValue(username).into(LoginPage.USERNAME_FIELD),
            Enter.theValue(password).into(LoginPage.PASSWORD_FIELD),
            Click.on(LoginPage.LOGIN_BUTTON),
            WaitUntil.the(SidebarMenu.DASHBOARD_LINK, isVisible()).forNoMoreThan(15).seconds()
        );
    }
}
