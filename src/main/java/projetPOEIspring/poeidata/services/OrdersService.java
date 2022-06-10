package projetPOEIspring.poeidata.services;

import projetPOEIspring.poeidata.models.Order;

import java.util.List;

public interface OrdersService {

    List<Order> getAll();
    Order getById(Integer id);
    Order create(Order order);
    Order update(Order order);
    void delete(Integer integer);
}
