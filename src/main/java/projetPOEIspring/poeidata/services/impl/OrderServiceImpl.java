package projetPOEIspring.poeidata.services.impl;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import projetPOEIspring.poeidata.exceptions.OrderException;
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
                .orElseThrow(()-> new UnknownResourceException("Order not found"));
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
        if(order.getPrix() < 0){
            throw new OrderException("The price need to be over 0 €");
        } else {
            orderToUpdate.setPrix(order.getPrix());
        }
        orderToUpdate.setDuree(order.getDuree());
        if(!order.getStatut().equals("Payée") || !order.getStatut().equals("Impayée")){
            throw new OrderException("The statut need to be \"Payée\" or \"Impayée\"");
        } else {
            orderToUpdate.setStatut(order.getStatut());
        }
        orderToUpdate.setNotes(order.getNotes());
        return this.orderRepository.save(orderToUpdate);
    }

    @Override
    public void delete(Integer id) {
        Order orderToDelete = this.getById(id);
        this.orderRepository.delete(orderToDelete);
    }
}
