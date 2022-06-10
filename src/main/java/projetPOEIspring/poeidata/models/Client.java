package projetPOEIspring.poeidata.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "client")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false)
    private String societe;

    @Column(nullable = false)
    private String mail;

    @Column(nullable = false)
    private String portable;

    @Column(nullable = false)
    private String fixe;

    @Column(nullable = false)
    private String statut;

    @Column(nullable = false)
    private String notes;

    @OneToMany(mappedBy = "client")
    private Set<Order> orders;
}
