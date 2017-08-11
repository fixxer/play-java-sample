package models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class UpdateResult {
    public final UUID id;
    @JsonSerialize(converter = LocalDateTimeToJsonConverter.class)
    public final LocalDateTime whenUpdated;
    public final Integer version;

    public UpdateResult(UUID id, LocalDateTime whenUpdated, Integer version) {
        this.id = id;
        this.whenUpdated = whenUpdated;
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateResult that = (UpdateResult) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(whenUpdated, that.whenUpdated) &&
                Objects.equals(version, that.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, whenUpdated, version);
    }

    @Override
    public String toString() {
        return "UpdateResult{" +
                "id=" + id +
                ", whenUpdated=" + whenUpdated +
                ", version=" + version +
                '}';
    }
}