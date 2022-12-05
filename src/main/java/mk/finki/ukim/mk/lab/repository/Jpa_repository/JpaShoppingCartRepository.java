package mk.finki.ukim.mk.lab.repository.Jpa_repository;

import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {
    ShoppingCart findByUser(User user);
}
