package mk.finki.ukim.mk.lab.repository.Impl;

import mk.finki.ukim.mk.lab.model.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepository {
    List<Order> ordersList;
    public OrderRepository()
    {
    ordersList=new ArrayList<>(10);
    }
    public void AddOrder(Order o)
    {
        ordersList.add(o);
    }
    public List<Order> ListOrders()
    {
        return ordersList.stream().toList();
    }


}
