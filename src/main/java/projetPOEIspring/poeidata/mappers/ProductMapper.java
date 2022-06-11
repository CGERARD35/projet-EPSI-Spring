package projetPOEIspring.poeidata.mappers;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import projetPOEIspring.poeidata.api.dto.ProductDto;
import projetPOEIspring.poeidata.models.Product;

@Component
@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto mapProductToDto(Product product);

    Product mapProductToModel(ProductDto productDto);

}
