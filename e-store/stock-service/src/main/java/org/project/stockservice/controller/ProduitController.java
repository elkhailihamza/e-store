package org.project.stockservice.controller;

import lombok.RequiredArgsConstructor;
import org.project.stockservice.dto.ProduitDTO;
import org.project.stockservice.service.ProduitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produit")
@RequiredArgsConstructor
public class ProduitController {
    private final ProduitService produitService;

    @PostMapping("/add/{productId}")
    public ResponseEntity<ProduitDTO> addToCart(@PathVariable long productId) {
        return ResponseEntity.ok(produitService.checkStockAndReturn(ProduitDTO.builder().id(productId).build()));
    }
}
