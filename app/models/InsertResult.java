package models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class InsertResult {
    public final UUID id;
    @JsonSerialize(converter = LocalDateTimeToJsonConverter.class)
    public final LocalDateTime whenCreated;

    public InsertResult(UUID id, LocalDateTime whenCreated) {
        this.id = id;
        this.whenCreated = whenCreated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InsertResult that = (InsertResult) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(whenCreated, that.whenCreated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, whenCreated);
    }

    @Override
    public String toString() {
        return "InsertResult{" +
                "id=" + id +
                ", whenCreated=" + whenCreated +
                '}';
    }
}
