package projetPOEIspring.poeidata.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import projetPOEIspring.poeidata.exceptions.OrderException;
import projetPOEIspring.poeidata.exceptions.UnknownResourceException;
import projetPOEIspring.poeidata.models.Client;
import projetPOEIspring.poeidata.models.Order;
import projetPOEIspring.poeidata.models.Product;
import projetPOEIspring.poeidata.repositories.OrderRepository;
import projetPOEIspring.poeidata.services.ClientService;
import projetPOEIspring.poeidata.services.OrdersService;
import projetPOEIspring.poeidata.services.ProductService;

import java.util.List;

@Service
public class OrderServiceImpl implements OrdersService {

    private final OrderRepository orderRepository;
    private final ClientService clientService;
    private final ProductService productService;

    @Lazy
    public OrderServiceImpl(OrderRepository orderRepository, ClientService clientService, ProductService productService) {
        this.orderRepository = orderRepository;
        this.clientService = clientService;
        this.productService = productService;
    }


    @Override
    public List<Order> getAll() {
        return this.orderRepository.findAll(Sort.by("client.nom").ascending());
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
            Order orderToCreate = new Order();
            Client clientToGet= this.clientService.getById(order.getClient().getId());
            orderToCreate.setClient(clientToGet);
            Product producToGet = this.productService.getById(order.getProduit().getId());
            orderToCreate.setProduit(producToGet);
            orderToCreate.setPrix(order.getPrix());
            orderToCreate.setNotes(order.getNotes());
            orderToCreate.setDuree(order.getDuree());
            orderToCreate.setDateCommande(order.getDateCommande());
            orderToCreate.setStatut(order.getStatut());
            return this.orderRepository.save(orderToCreate);
        }
    }

    @Override
    public Order update(Order order) {
        Order orderToUpdate = this.getById(order.getId());
        if(order.getPrix() < 0){
            throw new OrderException("The price need to be over 0 €");
        } else if(!(order.getStatut().equals("Payée") || order.getStatut().equals("Impayée"))){
            throw new OrderException("Le statut doit être Payée ou Impayée");
        } else {
            orderToUpdate.setDateCommande(order.getDateCommande());
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
