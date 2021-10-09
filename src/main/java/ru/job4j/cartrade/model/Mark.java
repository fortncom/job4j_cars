package ru.job4j.cartrade.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "marks")
public class Mark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public static Mark of(String name) {
        Mark mark = new Mark();
        mark.setName(name);
        return mark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Mark mark = (Mark) o;
        return id == mark.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Mark{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }
}
