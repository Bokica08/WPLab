package mk.finki.ukim.mk.lab.model;

import lombok.Data;

import java.io.Serializable;
@Data
public class UserFullname implements Serializable {
    String name;
    String surname;
}
