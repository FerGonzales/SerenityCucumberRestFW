import facts.NetflixPlans;
import models.users.*;
import questions.Q_GetRegister;
import questions.Q_GetUpdate;
import questions.Q_GetUsers;
import questions.Q_ResponseCode;
import tasks.GetUsers;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import tasks.RegisterUsers;
import tasks.UpdateUsers;
import utils.Utilitarios;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(SerenityRunner.class)
public class SerenityTests {

    private static final String restApiUrl = "http://localhost:5000/api";

    @Test
    public void getUsers() {
        Actor julian = Actor.named("Julian el trainer")
                .whoCan(CallAnApi.at(restApiUrl));
        julian.attemptsTo(GetUsers.frontPage(1));
        //assertThat(SerenityRest.lastResponse().statusCode()).isEqualTo(200);
        julian.should(
                seeThat("El codigo de respuesta", Q_ResponseCode.was(), equalTo(200))
        );
        Datum user = new Q_GetUsers().answeredBy(julian).getData().stream().filter(
                x -> x.getId() == 1).findFirst().orElse(null);
        julian.should(
                seeThat("Usuario no es nulo", act -> user, notNullValue())
        );
        julian.should(
                seeThat("El email del usuario", act -> user.getEmail(), equalTo("george.bluth@reqres.in")),
                seeThat("El email del usuario", act -> user.getAvatar(), equalTo("https://s3.amazonaws.com/uifaces/faces/twitter/calebogden/128.jpg"))

        );


    }

    @Test
    public void registerUserTest() {
        Actor julian = Actor.named("Julian el trainer")
                .whoCan(CallAnApi.at(restApiUrl));

        RegisterUser_Input registerUserInfo=new RegisterUser_Input();
        registerUserInfo.setName("morpheus");
        registerUserInfo.setJob("leader");
        registerUserInfo.setEmail("janet.weaver@reqres.in");
        registerUserInfo.setPassword("serenity");
        julian.attemptsTo(
                RegisterUsers.withInfo(registerUserInfo)
        );
        RegisterUser_Output vRO = new Q_GetRegister().answeredBy(julian);

        julian.should(
                seeThat("El codigo de respuesta", Q_ResponseCode.was(), equalTo(200)),
                seeThat("Contiene el campo Token,", act->vRO.getToken(),notNullValue())
        );
    }

    @Test
    public void updateUserTest() {
        Actor julian = Actor.named("Julian el trainer")
                .whoCan(CallAnApi.at(restApiUrl));

        UpdateUser_Input updateUserInfo=new UpdateUser_Input();
        updateUserInfo.setName("morpheus");
        updateUserInfo.setJob("zion resident");

        julian.attemptsTo(
                UpdateUsers.withInfo(updateUserInfo,2)
        );
        UpdateUser_Output updateUserOutput = new Q_GetUpdate().answeredBy(julian);
        Utilitarios utils= new Utilitarios();

        julian.should(
                seeThat("El codigo de respuesta", Q_ResponseCode.was(), equalTo(200)),
                seeThat("Actualizo el trabajo,", act->updateUserOutput.getJob(),equalTo("zion resident"))
                //seeThat("Contiene el campo Token,", act->updateUserOutput.getUpdatedAt(),equalTo(utils.getFechaActual()))
        );
    }

    @Test
    public void factTest() {
        Actor julian = Actor.named("Julian el trainer")
                .whoCan(CallAnApi.at(restApiUrl));

        julian.has(NetflixPlans.toViewSeries());
    }
//    @Test
//    public void getUsersFail()
//    {
//        Actor julian=Actor.named("Julian el trainer")
//                .whoCan(CallAnApi.at(restApiUrl));
//        julian.attemptsTo(GetUsers.frontPage(2));
//        //assertThat(SerenityRest.lastResponse().statusCode()).isEqualTo(400);
//        julian.should(
//                seeThat("El codigo de respuesta", ResponseCode.was(),equalTo(400))
//        );
//    }
    }
