package mk.finki.ukim.mk.lab.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "shop_orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long orderId;
    String balloonColor;
    String balloonSize;
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    LocalDateTime dateCreated;
    @ManyToOne
    User user;


    public Order(String balloonColor, String balloonSize, User user, LocalDateTime date) {
        this.balloonColor = balloonColor;
        this.balloonSize = balloonSize;
        this.user=user;
        this.dateCreated=date;
    }
    public Order() {
    }
    public Order(String balloonColor, User user)
    {
        this.balloonColor=balloonColor;
        this.user=user;
    }

}
