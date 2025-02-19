package org.project.stockservice.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.project.stockservice.dto.ProduitDTO;
import org.project.stockservice.entity.Produit;

@Mapper(componentModel = "spring")
public interface ProduitMapper {

    @Mapping(source = "categorie.id", target = "categorieId")
    ProduitDTO toDTO(Produit produit);

    @Mapping(source = "categorieId", target = "categorie.id")
    Produit toEntity(ProduitDTO produitDTO);
}
