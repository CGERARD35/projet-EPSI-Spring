package projetPOEIspring.poeidata.api.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {

    private Integer id;

    private String nom;

    private String prenom;

    private String societe;

    private String mail;

    private String fixe;

    private String portable;

    private String notes;

    private String statut;

    private List<OrderDto> orders;


}
