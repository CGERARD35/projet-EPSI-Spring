package projetPOEIspring.poeidata.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;
import projetPOEIspring.poeidata.api.dto.ClientDto;
import projetPOEIspring.poeidata.api.dto.OrderDto;
import projetPOEIspring.poeidata.models.Client;

import java.util.ArrayList;
import java.util.List;

@Component
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ClientMapper {

    @Mapping(target = "orders", expression = "java(getOrders(client))")
    ClientDto mapToClientDto(Client client);

    default List<OrderDto> getOrders(Client client) {
        List<OrderDto> orders = new ArrayList<>();
        if (client.getOrders() != null) {
            orders = client.getOrders().stream()
                    .map(order -> new OrderDto(
                            order.getId(),
                            order.getDateCommande(),
                            order.getPrix(),
                            order.getDuree(),
                            order.getStatut(),
                            order.getNotes(),
                            client.getId(),
                            null
                    )).toList();
        }
        return orders;
    }


    Client mapToClient (ClientDto clientDto);


}
