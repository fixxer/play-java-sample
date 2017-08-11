package repository;

import io.ebean.Ebean;
import io.ebean.EbeanServer;
import io.ebean.Transaction;
import models.Author;
import models.UpdateResult;
import play.db.ebean.EbeanConfig;

import javax.inject.Inject;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletionStage;

import static java.time.LocalDateTime.now;
import static java.util.concurrent.CompletableFuture.supplyAsync;

public class AuthorRepository {

    private final EbeanServer ebeanServer;
    private final DatabaseExecutionContext executionContext;
    private final DateProvider dateProvider;

    @Inject
    public AuthorRepository(EbeanConfig ebeanConfig, DatabaseExecutionContext executionContext, DateProvider dateProvider) {
        this.ebeanServer = Ebean.getServer(ebeanConfig.defaultServer());
        this.executionContext = executionContext;
        this.dateProvider = dateProvider;
    }

    public CompletionStage<Optional<UpdateResult>> update(UUID id, Author newAuthorData) {
        return supplyAsync(() -> {
            Transaction txn = ebeanServer.beginTransaction();
            Optional<UpdateResult> updateResult = Optional.empty();
            try {
                Author author = ebeanServer.find(Author.class).setId(id).findUnique();
                if (author != null) {
                    author.firstName = newAuthorData.firstName;
                    author.lastName = newAuthorData.lastName;
                    author.middleName = newAuthorData.middleName;
                    author.whenUpdated = dateProvider.getDate();
                    author.update();
                    txn.commit();
                    updateResult = Optional.of(new UpdateResult(id, author.whenUpdated, author.version));
                }
            } finally {
                txn.end();
            }
            return updateResult;
        }, executionContext);
    }
}
