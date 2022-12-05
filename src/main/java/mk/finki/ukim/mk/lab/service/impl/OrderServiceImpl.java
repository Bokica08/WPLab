package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.Exceptions.UserNotFound;
import mk.finki.ukim.mk.lab.Exceptions.WrongCredentialsException;
import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.repository.Jpa_repository.JpaShoppingCartRepository;
import mk.finki.ukim.mk.lab.repository.Jpa_repository.JpaUserRepository;
import mk.finki.ukim.mk.lab.service.OrderService;
import org.springframework.stereotype.Service;
import mk.finki.ukim.mk.lab.repository.Jpa_repository.JpaOrderRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
  private final JpaOrderRepository repository;
  private final JpaUserRepository userRepository;
  private final JpaShoppingCartRepository shoppingCartRepository;

    public OrderServiceImpl(JpaOrderRepository repository, JpaUserRepository userRepository, JpaShoppingCartRepository shoppingCartRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public List<Order> listAll()
    {
        return repository.findAll();
    }
    @Override
    public Order placeOrder(String color, String size, String username, LocalDateTime dateTime) throws UserNotFound {
        User user = userRepository.findByUsername(username).orElseThrow(UserNotFound::new);
        Order order = new Order(color,size,user,dateTime);
        return repository.save(order);
    }

    @Override
    public List<Order> searchByClient(String name) {
        User user = userRepository.findAllByUsernameLike(name).stream().findFirst().orElseThrow(WrongCredentialsException::new);
        return repository.findAllByUser(user);
    }

    @Override
    public List<Order> findByDateCreatedBetween(LocalDateTime time1, LocalDateTime time2) {
        return repository.findByDateCreatedBetween(time1,time2);
    }
}
