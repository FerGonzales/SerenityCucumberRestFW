package questions;

import models.users.RegisterUser_Output;
import models.users.UpdateUser_Output;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class Q_GetUpdate implements Question {

    public static void main(String[] args) {
    }

    @Override
    public UpdateUser_Output answeredBy(Actor actor) {

        return SerenityRest.lastResponse().as(UpdateUser_Output.class);
    }
}
