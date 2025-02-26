package org.project.stockservice.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.project.stockservice.dto.ProduitDTO;
import org.project.stockservice.entity.Produit;

@Mapper(componentModel = "spring", uses = {StockMapper.class})
public interface ProduitMapper {

    @Mappings({
            @Mapping(source = "categorie.id", target = "categorieId"),
            @Mapping(source = "stock", target = "stockDTO"),
            @Mapping(target = "stockDTO.produitDTO", ignore = true)
    })
    ProduitDTO toDTO(Produit produit);

    @Mappings({
            @Mapping(source = "categorieId", target = "categorie.id"),
            @Mapping(source = "stockDTO", target = "stock"),
            @Mapping(target = "stock.produit", ignore = true)
    })
    Produit toEntity(ProduitDTO produitDTO);
}
