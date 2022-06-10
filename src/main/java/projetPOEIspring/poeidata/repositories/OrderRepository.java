package projetPOEIspring.poeidata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projetPOEIspring.poeidata.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
