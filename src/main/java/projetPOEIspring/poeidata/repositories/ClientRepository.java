package projetPOEIspring.poeidata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projetPOEIspring.poeidata.models.Client;

import javax.persistence.criteria.CriteriaBuilder;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
}
