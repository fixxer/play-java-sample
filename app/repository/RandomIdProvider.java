package repository;

import javax.inject.Singleton;
import java.util.UUID;

@Singleton
public class RandomIdProvider implements IdProvider {
    @Override
    public UUID newId() {
        return UUID.randomUUID();
    }
}
