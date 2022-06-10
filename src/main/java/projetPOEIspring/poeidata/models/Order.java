package projetPOEIspring.poeidata.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "commande")
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

    @Column(name = "prix", columnDefinition = "NUMERIC", nullable = false)
    private Double prix;

    @Column(name = "duree", nullable = false)
    private String duree;

    @Column(name = "statut", nullable = false)
    private  String statut;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

//    OnetoOne
//    private Client client;

//    OneToone
//    private Produit produit;


}
