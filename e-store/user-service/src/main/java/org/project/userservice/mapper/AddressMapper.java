package org.project.userservice.mapper;

import org.mapstruct.Mapper;
import org.project.userservice.entity.Address;
import org.project.userservice.dto.AddressRequestDto;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressRequestDto toDto(Address address);
    Address toEntity(AddressRequestDto addressRequestDto);
}
