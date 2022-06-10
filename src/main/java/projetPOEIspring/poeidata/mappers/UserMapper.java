package projetPOEIspring.poeidata.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import projetPOEIspring.poeidata.api.dto.UserDto;
import projetPOEIspring.poeidata.models.User;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "password", ignore = true)
    UserDto mapToDto(User user);

    User mapToModel(UserDto userDto);
}
