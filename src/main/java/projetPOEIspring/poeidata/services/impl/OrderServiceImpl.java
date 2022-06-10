package projetPOEIspring.poeidata.services.impl;

import org.slf4j.Logger;
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
        if(order.getPrix() < 0){
            throw new OrderException("The price need to be over 0 €");
        } else if(!(order.getStatut().equals("Payée") || order.getStatut().equals("Impayée"))){
            throw new OrderException("Le statut doit être Payée ou Impayée");
        } else {
            return this.orderRepository.save(order);
        }
    }

    @Override
    public Order update(Order order) {
        Order orderToUpdate = this.getById(order.getId());
        if(order.getPrix() < 0){
            throw new OrderException("The price need to be over 0 €");
        } else if(!order.getStatut().equals("Payée") || !order.getStatut().equals("Impayée")){
            throw new OrderException("Le statut doit être Payée ou Impayée");
        } else {
            orderToUpdate.setDateCommande(order.getDateCommande());
            orderToUpdate.setProduit(order.getProduit());
            orderToUpdate.setClient(order.getClient());
            orderToUpdate.setNotes(order.getNotes());
            orderToUpdate.setPrix(order.getPrix());
            orderToUpdate.setDuree(order.getDuree());
            orderToUpdate.setStatut(order.getStatut());
            return this.orderRepository.save(orderToUpdate);
        }

    }

    @Override
    public void delete(Integer id) {
        Order orderToDelete = this.getById(id);
        this.orderRepository.delete(orderToDelete);
    }
}
