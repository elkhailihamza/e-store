package org.project.userservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.project.userservice.entity.User;
import org.project.userservice.vm.AuthResponseVM;
@Mapper(componentModel = "spring")

public interface UserMapper {
    @Mapping(source = "addresses", target = "addresses")
    AuthResponseVM toDto(User user);
}
