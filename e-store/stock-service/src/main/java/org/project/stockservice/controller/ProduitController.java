package org.project.stockservice.controller;

import lombok.RequiredArgsConstructor;
import org.project.stockservice.dto.ProduitDTO;
import org.project.stockservice.dto.StockDTO;
import org.project.stockservice.service.ProduitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produit")
@RequiredArgsConstructor
public class ProduitController {
    private final ProduitService produitService;

    @PostMapping("/create")
    public ResponseEntity<ProduitDTO> createNewProduct(@RequestBody ProduitDTO produitDTO, @RequestBody StockDTO stockDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(produitService.createProduct(produitDTO, stockDTO));
    }

    @PostMapping("/add/{productId}")
    public ResponseEntity<ProduitDTO> addToCart(@PathVariable long productId) {
        return ResponseEntity.ok(produitService.checkStockAndReturn(ProduitDTO.builder().id(productId).build()));
    }
}
