package org.project.stockservice.service;

import org.project.stockservice.dto.ProduitDTO;

public interface ProduitService {
    ProduitDTO checkStockAndReturn(ProduitDTO produitDTO);
}
