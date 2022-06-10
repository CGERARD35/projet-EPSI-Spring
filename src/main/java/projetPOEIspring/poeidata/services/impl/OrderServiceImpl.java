package projetPOEIspring.poeidata.services.impl;

import org.springframework.stereotype.Service;
import projetPOEIspring.poeidata.exceptions.UnknownResourceException;
import projetPOEIspring.poeidata.models.Order;
import projetPOEIspring.poeidata.repositories.OrderRepository;
import projetPOEIspring.poeidata.services.OrdersService;

import java.util.List;

@Service
public class OrderServiceImpl implements OrdersService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAll() {
        return this.orderRepository.findAll();
    }

    @Override
    public Order getById(Integer id) {
        return this.orderRepository.findById(id)
                .orElseThrow(()-> new UnknownResourceException());
    }

    @Override
    public Order create(Order order) {
        order.setId(null);
        return this.orderRepository.save(order);
    }

    @Override
    public Order update(Order order) {
        Order orderToUpdate = this.getById(order.getId());
        orderToUpdate.setDateCommande(order.getDateCommande());
        orderToUpdate.setPrix(order.getPrix());
        orderToUpdate.setDuree(order.getDuree());
        orderToUpdate.setStatut(order.getStatut());
        orderToUpdate.setNotes(order.getNotes());
        return this.orderRepository.save(orderToUpdate);
    }

    @Override
    public void delete(Integer integer) {

    }
}
