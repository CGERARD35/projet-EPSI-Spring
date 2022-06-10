package projetPOEIspring.poeidata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projetPOEIspring.poeidata.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
