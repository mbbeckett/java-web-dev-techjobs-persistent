package org.launchcode.javawebdevtechjobspersistent.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@MappedSuperclass
public abstract class AbstractEntity {

//    FIELDS
    @Id
    @GeneratedValue
    private int id;

    @NotBlank
    @NotNull
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters.")
    private String name;

//    CONSTRUCTOR
//    public AbstractEntity(int id, String name) {
//        this.id = id;
//        this.name = name;
//    }

//    GETTERS SETTERS
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    METHODS
    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity entity = (AbstractEntity) o;
        return id == entity.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}