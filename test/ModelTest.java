import io.ebean.PagedList;
import models.Author;
import models.Book;
import models.InsertResult;
import models.UpdateResult;
import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.test.WithApplication;
import repository.AuthorRepository;
import repository.BookRepository;
import repository.DateProvider;
import repository.IdProvider;

import javax.inject.Provider;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletionStage;

import static java.time.LocalDateTime.now;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static play.inject.Bindings.bind;

public class ModelTest extends WithApplication {

    private static final UUID LEWIS_CARROL_ID = UUID.fromString("bdd6741d-71b9-493c-9c31-38af19b9e27c");
    public static final UUID DETECTIVE_GENRE_ID = UUID.fromString("5d85ce82-3fdf-4251-82dd-ececf927935b");

    private IdProvider idProviderMock = mock(IdProvider.class);
    private DateProvider dateProviderMock = mock(DateProvider.class);

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder()
                .overrides(bind(IdProvider.class).to((Provider<? extends IdProvider>) () -> idProviderMock))
                .overrides(bind(DateProvider.class).to((Provider<? extends DateProvider>) () -> dateProviderMock))
                .build();
    }

    @Test
    public void updateAuthor() {
        final AuthorRepository authorRepository = app.injector().instanceOf(AuthorRepository.class);
        LocalDateTime now = now();
        when(dateProviderMock.getDate()).thenReturn(now);

        final CompletionStage<Optional<UpdateResult>> stage = authorRepository.update(LEWIS_CARROL_ID, new Author());

        await().atMost(1, SECONDS).until(() ->
                assertThat(stage.toCompletableFuture())
                        .isCompletedWithValue(Optional.of(new UpdateResult(LEWIS_CARROL_ID, now, 2))));
    }

    @Test
    public void insertBook() throws Exception {
        final BookRepository bookRepository = app.injector().instanceOf(BookRepository.class);
        LocalDateTime now = now();
        when(dateProviderMock.getDate()).thenReturn(now);
        UUID uuid = UUID.randomUUID();
        when(idProviderMock.newId()).thenReturn(uuid);

        final CompletionStage<Optional<InsertResult>> stage = bookRepository.insert(new Book());

        await().atMost(1, SECONDS).until(() ->
                assertThat(stage.toCompletableFuture())
                        .isCompletedWithValue(Optional.of(new InsertResult(uuid, now))));
    }

    @Test
    public void pagination() {
        final BookRepository bookRepository = app.injector().instanceOf(BookRepository.class);
        CompletionStage<PagedList<Book>> stage = bookRepository.findByGenrePaged(DETECTIVE_GENRE_ID, 0, 2);

        // Test the completed result
        await().atMost(1, SECONDS).until(() ->
            assertThat(stage.toCompletableFuture()).isCompletedWithValueMatching(bookPagedList ->
                bookPagedList.getTotalCount() == 3 &&
                bookPagedList.getTotalPageCount() == 2 &&
                bookPagedList.getList().size() == 2
            )
        );
    }

}
