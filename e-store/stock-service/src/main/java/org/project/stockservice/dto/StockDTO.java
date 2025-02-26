package org.project.stockservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.project.stockservice.entity.Produit;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockDTO {
    private Long id;
    private ProduitDTO produitDTO;
    private int quantiteDisponible;
    private int quantiteOriginal;
    private int seuilMinimum;
}
