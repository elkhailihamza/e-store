package org.project.stockservice.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Context;
import org.project.stockservice.dto.StockDTO;
import org.project.stockservice.entity.Stock;

@Mapper(componentModel = "spring", uses = {ProduitMapper.class})
public interface StockMapper {

    @Mappings({
            @Mapping(source = "produit", target = "produitDTO")
    })
    StockDTO toDTO(Stock stock);

    @Mappings({
            @Mapping(source = "produitDTO", target = "produit")
    })
    Stock toEntity(StockDTO stockDTO);
}
