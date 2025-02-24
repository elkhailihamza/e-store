package org.project.stockservice.service;

import org.project.stockservice.dto.ProduitDTO;
import org.project.stockservice.dto.StockDTO;
import org.project.stockservice.dto.paged.PagedResult;

public interface ProduitService {
    ProduitDTO checkStockAndReturn(ProduitDTO produitDTO);
    ProduitDTO createProduct(ProduitDTO produitDTO);
    PagedResult<ProduitDTO> getPagedProduits(int pageNum, String filterBy);
}
