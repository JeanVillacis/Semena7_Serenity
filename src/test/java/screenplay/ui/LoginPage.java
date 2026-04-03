package screenplay.ui;

import net.serenitybdd.screenplay.targets.Target;

public class LoginPage {

    public static final Target USERNAME_FIELD = Target.the("campo de usuario")
        .locatedBy("//input[@placeholder='ejemplo@evaluador.com']");

    public static final Target PASSWORD_FIELD = Target.the("campo de contraseña")
        .locatedBy("//input[@type='password']");

    public static final Target LOGIN_BUTTON = Target.the("botón iniciar sesión")
        .locatedBy("//button[contains(text(),'Iniciar Sesión')]");
}
