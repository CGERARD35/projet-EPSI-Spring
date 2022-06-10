package projetPOEIspring.poeidata.services;

import projetPOEIspring.poeidata.models.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAll();

    Product getById(Integer id);

    Product createProduct(Product product);

    void deleteProduct(Integer id);

    Product updateProduct(Product product);
}
