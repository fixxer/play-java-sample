package repository;

import com.google.inject.ImplementedBy;

import java.util.UUID;

@ImplementedBy(RandomIdProvider.class)
public interface IdProvider {
    UUID newId();
}
