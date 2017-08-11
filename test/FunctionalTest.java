import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import controllers.routes;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import play.mvc.Http;
import play.mvc.Result;
import play.test.Helpers;
import play.test.WithApplication;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static play.api.test.CSRFTokenHelper.addCSRFToken;
import static play.test.Helpers.*;

// Use FixMethodOrder to run the tests sequentially
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FunctionalTest extends WithApplication {

    @Test
    public void addBook() throws Exception {
        JsonNode jsonNode = new ObjectMapper().readTree(
                "{\n" +
                        "\t\"name\": \"Alice's Adventures in Wonderland\",\n" +
                        "\t\"author\": {\n" +
                        "\t\t\"id\": \"bdd6741d-71b9-493c-9c31-38af19b9e27c\"\n" +
                        "\t},\n" +
                        "\t\"year\": 1865,\n" +
                        "\t\"edition\": 1,\n" +
                        "\t\"genreCollection\": [\n" +
                        "{\n" +
                        "\t\t\t\"id\": \"f022820c-cb84-4981-9184-46b6f9a17de8\"\n" +
                        "\t\t}\n" +
                        "\t]\n" +
                        "}\n"
        );
        Http.RequestBuilder request = new Http.RequestBuilder().method("POST")
                .bodyJson(jsonNode)
                .uri(routes.BookController.add().url());
        Result result = route(app, request);

        assertThat(result.status()).isEqualTo(OK);
    }

    @Test
    public void loadAssociatedBookCollection() throws Exception {
        Result result = route(app, routes.GenreController.loadAssociatedBookCollection(
                UUID.fromString("f022820c-cb84-4981-9184-46b6f9a17de8"), 0, 50));

        assertThat(result.status()).isEqualTo(OK);
    }

    @Test
    public void updateAuthor() throws Exception {
        JsonNode jsonNode = new ObjectMapper().readTree(
                "{\n" +
                        "\t\"lastName\": \"Dodgson\",\n" +
                        "\t\"firstName\": \"Charles\",\n" +
                        "\t\"middleName\": \"Lutwidge\"\n" +
                        "}\n"
        );
        Http.RequestBuilder request = new Http.RequestBuilder().method("PUT")
                .bodyJson(jsonNode)
                .uri(routes.AuthorController.update(UUID.fromString("bdd6741d-71b9-493c-9c31-38af19b9e27c")).url());
        Result result = route(app, request);

        assertThat(result.status()).isEqualTo(OK);
    }
}
