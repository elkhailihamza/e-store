package org.project.stockservice.controller;

import lombok.RequiredArgsConstructor;
import org.project.stockservice.dto.ProduitDTO;
import org.project.stockservice.dto.StockDTO;
import org.project.stockservice.dto.paged.PagedResult;
import org.project.stockservice.service.ProduitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/p")
@RequiredArgsConstructor
public class ProduitController {
    private final ProduitService produitService;

    @PostMapping("/create")
    public ResponseEntity<ProduitDTO> createNewProduct(@RequestBody ProduitDTO produitDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(produitService.createProduct(produitDTO));
    }

    @PostMapping("/add/{productId}")
    public ResponseEntity<ProduitDTO> addToCart(@PathVariable long productId) {
        return ResponseEntity.ok(produitService.checkStockAndReturn(ProduitDTO.builder().id(productId).build()));
    }

    @GetMapping("/catalog")
    public ResponseEntity<PagedResult<ProduitDTO>> getProduits(@RequestParam(name = "page", defaultValue = "1") int pageNum, @RequestParam(name = "by", defaultValue = "name") String filterBy) {
        return ResponseEntity.ok(produitService.getPagedProduits(pageNum, filterBy));
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello from product controller");
    }
}
