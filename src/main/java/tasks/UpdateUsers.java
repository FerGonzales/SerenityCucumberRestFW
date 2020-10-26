package tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Put;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class UpdateUsers implements Task {
    private final Object userInfo;
    private int page;

    public UpdateUsers(Object userInfo, int page) {
        this.userInfo = userInfo;
        this.page=page;
    }

    public static Performable withInfo(Object userInfo,int page) {
        return instrumented(UpdateUsers.class, userInfo,page);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Put.to("/users/"+page).with(
                        requestSpecification -> requestSpecification
                                .contentType(ContentType.JSON)
                                .body(userInfo)
                )
        );
    }
}
