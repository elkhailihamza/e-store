package org.project.stockservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProduitDTO {
    private Long id;
    private String nom;
    private String description;
    private double prix;
    private long categorieId;
    private StockDTO stockDTO;
}
