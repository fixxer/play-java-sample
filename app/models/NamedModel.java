package models;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class NamedModel extends BaseModel {
    public String name;
}
