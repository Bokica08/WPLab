package mk.finki.ukim.mk.lab.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Data
@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    @DateTimeFormat(pattern = "yyyy-MM-dd 'T' HH:mm:ss")
    private LocalDateTime dateCreated;
    @ManyToMany
    private List<Order> orders;

    public ShoppingCart(User user) {
        this.user = user;
        this.dateCreated = LocalDateTime.now();
        orders=new ArrayList<>();
    }

    public ShoppingCart() {

    }
}
