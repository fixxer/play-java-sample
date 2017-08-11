package controllers;

import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;
import repository.BookRepository;

import javax.inject.Inject;
import java.util.UUID;
import java.util.concurrent.CompletionStage;

public class GenreController extends Controller {

    private final HttpExecutionContext httpExecutionContext;
    private final BookRepository repository;

    @Inject
    public GenreController(BookRepository repository, HttpExecutionContext httpExecutionContext) {
        this.repository = repository;
        this.httpExecutionContext = httpExecutionContext;
    }

    public CompletionStage<Result> loadAssociatedBookCollection(UUID genreId, int page, int pageSize) {
        return repository.findByGenrePaged(genreId, page, pageSize)
                .thenApplyAsync(list -> ok(Json.toJson(list.getList())), httpExecutionContext.current());
    }
}
