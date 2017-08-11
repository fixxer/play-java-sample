package models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class Genre extends NamedModel {
    @ManyToMany
    public Set<Book> bookCollection;
}
