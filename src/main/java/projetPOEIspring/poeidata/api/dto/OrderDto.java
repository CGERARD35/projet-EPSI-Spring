package projetPOEIspring.poeidata.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Integer id;

    private Date dateCommande;

    private Double prix;

    private String duree;

    private  String statut;

    private String notes;

//    private String clientNom;
//    private String prenomClient;
//    private String companyClient;
//
//    private String nomProduit;
//    private String prixProduit;

}
