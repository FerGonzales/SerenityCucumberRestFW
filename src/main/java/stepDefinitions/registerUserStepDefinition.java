package stepDefinitions;

import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import models.users.RegisterUser_Input;
import models.users.RegisterUser_Output;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.junit.runner.RunWith;
import questions.Q_GetRegister;
import questions.Q_ResponseCode;
import tasks.RegisterUsers;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(SerenityRunner.class)
public class registerUserStepDefinition {
    private final String restApiUrl = "http://localhost:5000/api";
    Actor julian;
    RegisterUser_Input registerUserInfo;
    @Dado("^Julian es un cliente que quiere administrar sus productos bancarios$")
    public void julianEsUnClienteQueQuiereAdministrarSusProductosBancarios() {
         julian = Actor.named("Julian el trainer")
                .whoCan(CallAnApi.at(restApiUrl));
    }

    @Cuando("^el envia la informacion requerida para el registro$")
    public void elEnviaLaInformacionRequeridaParaElRegistro() {
        registerUserInfo=new RegisterUser_Input();
        registerUserInfo.setName("morpheus");
        registerUserInfo.setJob("leader");
        registerUserInfo.setEmail("janet.weaver@reqres.in");
        registerUserInfo.setPassword("serenity");
        julian.attemptsTo(
                RegisterUsers.withInfo(registerUserInfo)
        );
    }

    @Entonces("^el debe obtener una cuenta virtual para poder ingresar cuando lo requiera$")
    public void elDebeObtenerUnaCuentaVirtualParaPoderIngresarCuandoLoRequiera() {
        RegisterUser_Output vRO = new Q_GetRegister().answeredBy(julian);

        julian.should(
                seeThat("El codigo de respuesta", Q_ResponseCode.was(), equalTo(200)),
                seeThat("Contiene el campo Token,", act->vRO.getToken(),notNullValue())
        );
    }

}
