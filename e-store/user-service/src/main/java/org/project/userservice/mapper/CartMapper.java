package org.project.userservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.project.userservice.dto.CartDto;
import org.project.userservice.entity.Cart;
import org.project.userservice.vm.CartVM;

@Mapper(componentModel = "spring")
public interface CartMapper {
    Cart toEntity(CartDto dto);

    @Mapping(target = "id", ignore = true) // On ne modifie pas l'ID existant
    void updateCartFromDto(CartDto dto, @MappingTarget Cart cart);

    CartVM toVm(Cart cart);
}
