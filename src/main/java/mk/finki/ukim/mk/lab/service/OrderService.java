package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService{

    List<Order> listAll();
    Order placeOrder(String balloonColor, String size, String username, LocalDateTime dateTime);
    List<Order> searchByClient(String name);
    List<Order> findByDateCreatedBetweenAndUser(LocalDateTime time1, LocalDateTime time2, User user);

}
