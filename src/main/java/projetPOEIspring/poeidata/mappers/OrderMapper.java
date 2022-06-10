package projetPOEIspring.poeidata.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;
import projetPOEIspring.poeidata.api.dto.OrderDto;
import projetPOEIspring.poeidata.api.dto.OrderProductClientDtoGetAll;
import projetPOEIspring.poeidata.models.Order;

@Component
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderMapper {

    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "produit.id", target = "produitId")
    OrderDto mapToDto(Order order);

    @Mapping(source = "clientId", target = "client.id")
    @Mapping(source = "produitId", target = "produit.id")
    Order mapToModel(OrderDto orderDto);

    OrderProductClientDtoGetAll mapToDtoOrder(Order order);

    Order mapToModelOrder(OrderProductClientDtoGetAll OrderProductClientDto);

}
