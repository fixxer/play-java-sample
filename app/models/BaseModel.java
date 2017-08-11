package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.ebean.Model;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
public class BaseModel extends Model {
    @Id
    public UUID id;
    @JsonIgnore
    public LocalDateTime whenCreated;
    @JsonIgnore
    public LocalDateTime whenUpdated;
    @JsonIgnore
    public State state;
    @Version @JsonIgnore
    public Integer version;
}
