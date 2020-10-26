package tasks;

import interactions.Get;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class GetUsers implements Task {
    private  final int page;

    public GetUsers(int page) {
        this.page = page;
    }

    public  static Performable frontPage(int page){
        return instrumented(GetUsers.class,page);
    }
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource("/users?page="+page)
                .with(requestSpecification ->
                        requestSpecification.contentType(ContentType.JSON)
                        .header("header1", "value1")
                )
        );
    }
}
