package repository;

import com.google.inject.ImplementedBy;

import java.time.LocalDateTime;

@ImplementedBy(NowDateProvider.class)
public interface DateProvider {
    LocalDateTime getDate();
}
