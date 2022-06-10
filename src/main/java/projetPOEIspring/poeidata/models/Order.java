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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "customer_id", nullable = false)
//    private Client client;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "produit_id", nullable = false)
//    private Product produit;


}
