package org.project.stockservice.service;

import org.project.stockservice.dto.ProduitDTO;
import org.project.stockservice.dto.StockDTO;

public interface ProduitService {
    ProduitDTO checkStockAndReturn(ProduitDTO produitDTO);
    ProduitDTO createProduct(ProduitDTO produitDTO, StockDTO stockDTO);
}
