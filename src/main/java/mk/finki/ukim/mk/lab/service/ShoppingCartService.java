package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.Exceptions.UserNotFound;
import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.ShoppingCart;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartService {
    List<Order> getListForUser(String username) throws UserNotFound;
    Optional<ShoppingCart> findById(Long id) throws UserNotFound;
}
