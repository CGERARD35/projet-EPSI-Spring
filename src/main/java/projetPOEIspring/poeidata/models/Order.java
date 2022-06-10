package projetPOEIspring.poeidata.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "order")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "date_commande", nullable = false)
    private Date dateCommande;

    @Column(name = "prix", nullable = false)
    private Double prix;

    @Column(name = "duree", nullable = false)
    private String duree;

    @Column(name = "statut", nullable = false)
    private  String statut;

    @Column(name = "notes")
    private String notes;

//    OneToMany
//    private List<Client> clients;

//    OneToMany
//    private List<Produit> produit;


}
