package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Author;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;
import repository.AuthorRepository;

import javax.inject.Inject;
import java.util.UUID;
import java.util.concurrent.CompletionStage;

public class AuthorController extends Controller {

    private final AuthorRepository authorRepository;
    private final HttpExecutionContext executionContext;

    @Inject
    public AuthorController(AuthorRepository authorRepository, HttpExecutionContext executionContext) {
        this.authorRepository = authorRepository;
        this.executionContext = executionContext;
    }

    public CompletionStage<Result> update(UUID id) {
        JsonNode json = request().body().asJson();
        Author newAuthorData = Json.fromJson(json, Author.class);
        return authorRepository.update(id, newAuthorData).thenApplyAsync(
                result -> result
                        .map(update -> ok(Json.toJson(update)))
                        .orElse(badRequest("Author nor found: " + id)),
                executionContext.current()
        );
    }
}
