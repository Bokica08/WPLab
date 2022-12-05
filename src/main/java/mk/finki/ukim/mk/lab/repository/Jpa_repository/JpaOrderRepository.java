package mk.finki.ukim.mk.lab.repository.Jpa_repository;

import mk.finki.ukim.mk.lab.model.Order;

import mk.finki.ukim.mk.lab.model.User;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface JpaOrderRepository extends JpaRepository<Order,Long> {
    List<Order> findAllByUser(User user);
    List<Order> findByDateCreatedBetween(LocalDateTime time1, LocalDateTime time2);
}
