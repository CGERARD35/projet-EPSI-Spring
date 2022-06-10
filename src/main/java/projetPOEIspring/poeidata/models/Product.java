package projetPOEIspring.poeidata.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "produit")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100)
    private String nom;

    @Column(columnDefinition = "NUMERIC")
    private Double prix;

    @Column(length = 50)
    private String type;

    @Column(length = 20)
    private String statut;

    @OneToMany(mappedBy = "produit")
    private Set<Order> commandes;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prix=" + prix +
                ", type='" + type + '\'' +
                ", statut='" + statut + '\'' +
                ", commandes=" + commandes +
                '}';
    }
}
