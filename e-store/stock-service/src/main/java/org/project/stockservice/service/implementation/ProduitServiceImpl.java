package org.project.stockservice.service.implementation;

import lombok.RequiredArgsConstructor;
import org.project.stockservice.dto.ProduitDTO;
import org.project.stockservice.dto.mapper.ProduitMapper;
import org.project.stockservice.entity.Produit;
import org.project.stockservice.entity.Stock;
import org.project.stockservice.exception.ItemConstraintViolationException;
import org.project.stockservice.exception.ItemNotFoundException;
import org.project.stockservice.repository.ProduitRepository;
import org.project.stockservice.service.ProduitService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProduitServiceImpl implements ProduitService {
    private final ProduitRepository produitRepository;
    private final ProduitMapper produitMapper;

    @Override
    public ProduitDTO checkStockAndReturn(ProduitDTO produitDTO) {
        Produit produit = produitRepository.findById(produitDTO.getId())
                .orElseThrow(() -> new ItemNotFoundException("Product not found!"));

        Stock productStock = produit.getStock();
        if (productStock.getQuantiteDisponible() <= 0) {
            throw new ItemConstraintViolationException("Product out of stock!");
        }
        // connect to user service and add productId to panier and grab user id with it, after that then return product to controller for frontend
        return produitMapper.toDTO(produit);
    }
}
