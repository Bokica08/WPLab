package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.Exceptions.UserNotFound;
import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.repository.Jpa_repository.JpaOrderRepository;
import mk.finki.ukim.mk.lab.repository.Jpa_repository.JpaShoppingCartRepository;
import mk.finki.ukim.mk.lab.repository.Jpa_repository.JpaUserRepository;
import mk.finki.ukim.mk.lab.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartImpl implements ShoppingCartService {
    private final JpaShoppingCartRepository shoppingCartRepository;
    private final JpaUserRepository userRepository;
    private final JpaOrderRepository orderRepository;

    public ShoppingCartImpl(JpaShoppingCartRepository shoppingCartRepository, JpaUserRepository userRepository, JpaOrderRepository orderRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getListForUser(String username) throws UserNotFound {
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFound::new);
        return this.shoppingCartRepository.findByUser(user).getOrders();
    }

    @Override
    public Optional<ShoppingCart> findById(Long id) throws UserNotFound {
        return Optional.ofNullable(shoppingCartRepository.findById(id).orElseThrow(UserNotFound::new));
    }
}
