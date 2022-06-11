package projetPOEIspring.poeidata.api.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderClientDto {

    private Integer id;

    private String nom;

    private String prenom;

    private String societe;


}
