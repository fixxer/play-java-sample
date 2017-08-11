package repository;

import io.ebean.Ebean;
import io.ebean.EbeanServer;
import io.ebean.PagedList;
import io.ebean.Transaction;
import models.Book;
import models.InsertResult;
import play.db.ebean.EbeanConfig;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class BookRepository {

    private final EbeanServer ebeanServer;
    private final DatabaseExecutionContext executionContext;
    private final IdProvider idProvider;
    private final DateProvider dateProvider;

    @Inject
    public BookRepository(EbeanConfig ebeanConfig,
                          DatabaseExecutionContext executionContext,
                          IdProvider idProvider,
                          DateProvider dateProvider) {
        this.ebeanServer = Ebean.getServer(ebeanConfig.defaultServer());
        this.executionContext = executionContext;
        this.idProvider = idProvider;
        this.dateProvider = dateProvider;
    }

    public CompletionStage<PagedList<Book>> findByGenrePaged(UUID genreId, int page, int pageSize) {
        return supplyAsync(() -> ebeanServer.find(Book.class).where()
                        .eq("genreCollection.id", genreId)
                        .setFirstRow(page * pageSize)
                        .setMaxRows(pageSize)
                        .findPagedList(),
                executionContext);
    }

    public CompletionStage<Optional<InsertResult>> insert(Book newBookData) {
        return supplyAsync(() -> {
                    Optional<InsertResult> result = Optional.empty();
                    Transaction txn = ebeanServer.beginTransaction();
                    try {
                        LocalDateTime now = dateProvider.getDate();
                        newBookData.id = idProvider.newId();
                        newBookData.whenCreated = now;
                        newBookData.whenUpdated = now;
                        newBookData.save();
                        txn.commit();
                        result = Optional.of(new InsertResult(newBookData.id, now));
                    } finally {
                        txn.end();
                    }
                    return result;
                },
                executionContext);
    }
}
