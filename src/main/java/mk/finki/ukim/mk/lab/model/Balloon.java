package mk.finki.ukim.mk.lab.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Balloon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String name;
    String description;
    @ManyToOne
    private Manufacturer manufacturer;

    public Balloon(String name, String description,Manufacturer manufacturer) {
        this.name = name;
        this.description = description;
        this.manufacturer=manufacturer;
    }

    public Balloon() {

    }
}
