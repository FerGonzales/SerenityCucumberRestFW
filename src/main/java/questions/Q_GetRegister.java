package questions;

import models.users.RegisterUser_Output;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class Q_GetRegister implements Question {
    @Override
    public RegisterUser_Output answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(RegisterUser_Output.class);
    }
}
