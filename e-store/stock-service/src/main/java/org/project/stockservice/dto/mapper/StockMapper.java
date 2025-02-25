package org.project.stockservice.dto.mapper;

import org.mapstruct.Mapper;
import org.project.stockservice.dto.StockDTO;
import org.project.stockservice.entity.Stock;

@Mapper(componentModel = "spring")
public interface StockMapper {
    StockDTO toDTO(Stock stock);
    Stock toEntity(StockDTO stockDTO);
}
