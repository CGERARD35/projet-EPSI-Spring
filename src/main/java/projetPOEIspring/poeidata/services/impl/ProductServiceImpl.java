package projetPOEIspring.poeidata.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import projetPOEIspring.poeidata.exceptions.UnknownResourceException;
import projetPOEIspring.poeidata.models.Order;
import projetPOEIspring.poeidata.models.Product;
import projetPOEIspring.poeidata.repositories.OrderRepository;
import projetPOEIspring.poeidata.repositories.ProductRepository;
import projetPOEIspring.poeidata.services.OrdersService;
import projetPOEIspring.poeidata.services.ProductService;

import java.util.List;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private  OrderRepository orderRepository;

    @Autowired
    private OrdersService ordersService;


    @Override
    public List<Product> getAll() {
        return this.productRepository.findAll(Sort.by("nom").ascending());
    }

    @Override
    public Product getById(Integer id) {
        return this.productRepository.findById(id).orElseThrow(() -> new UnknownResourceException("Pas de produit trouvé pour cet identifiant."));
    }

    @Override
    public Product createProduct(Product product) {
        return this.productRepository.save(product);
    }

    @Override
    public void deleteProduct(Integer id) {
        Product productToDelete = this.getById(id);
        this.productRepository.delete(productToDelete);
    }

    @Override
    public Product updateProduct(Product product) {
        Product productToUpdate = this.getById(product.getId());
        productToUpdate.setNom(product.getNom());
        productToUpdate.setPrix(product.getPrix());
        productToUpdate.setType(product.getType());
        productToUpdate.setStatut(product.getStatut());
        Set<Order> orders = product.getCommandes();
        productToUpdate.setCommandes(orders);
        return productRepository.save(productToUpdate);
    }
}
