package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Book;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import repository.BookRepository;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

public class BookController extends Controller {

    private final HttpExecutionContext httpExecutionContext;
    private final BookRepository repository;

    @Inject
    public BookController(BookRepository repository, HttpExecutionContext httpExecutionContext) {
        this.repository = repository;
        this.httpExecutionContext = httpExecutionContext;
    }

    @BodyParser.Of(BodyParser.Json.class)
    public CompletionStage<Result> add() {
        JsonNode json = request().body().asJson();
        Book book = Json.fromJson(json, Book.class);
        return repository.insert(book).thenApplyAsync(
                result -> result.map(i -> ok(Json.toJson(i))).orElse(badRequest("Error adding book")),
                httpExecutionContext.current()
        );
    }
}
