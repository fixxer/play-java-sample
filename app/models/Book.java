package models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Set;

@Entity
public class Book extends NamedModel {
    public String year;
    public String edition;
    @ManyToOne
    public Author author;
    @ManyToMany @JsonIgnore
    public Set<Genre> genreCollection;
}
