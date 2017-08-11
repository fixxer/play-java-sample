package models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Author extends BaseModel {
    public String lastName;
    public String firstName;
    public String middleName;
    @OneToMany @JsonIgnore
    public Set<Book> bookCollection;
}
