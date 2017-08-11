package repository;

import javax.inject.Singleton;
import java.time.LocalDateTime;

@Singleton
public class NowDateProvider implements DateProvider {
    @Override
    public LocalDateTime getDate() {
        return LocalDateTime.now();
    }
}
