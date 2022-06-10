package projetPOEIspring.poeidata.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;
import projetPOEIspring.poeidata.api.dto.OrderDto;
import projetPOEIspring.poeidata.models.Order;

@Component
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderMapper {

    OrderDto mapToDto(Order order);

    Order mapToModel(OrderDto orderDto);

}
