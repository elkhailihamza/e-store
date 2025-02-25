package org.project.stockservice.service.implementation;

import lombok.RequiredArgsConstructor;
import org.project.stockservice.dto.ProduitDTO;
import org.project.stockservice.dto.StockDTO;
import org.project.stockservice.dto.mapper.ProduitMapper;
import org.project.stockservice.dto.mapper.StockMapper;
import org.project.stockservice.dto.paged.PagedResult;
import org.project.stockservice.entity.Produit;
import org.project.stockservice.entity.Stock;
import org.project.stockservice.exception.ItemConstraintViolationException;
import org.project.stockservice.exception.ItemNotFoundException;
import org.project.stockservice.repository.ProduitRepository;
import org.project.stockservice.repository.StockRepository;
import org.project.stockservice.service.ProduitService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProduitServiceImpl implements ProduitService {
    private final ProduitRepository produitRepository;
    private final StockRepository stockRepository;
    private final ProduitMapper produitMapper;
    private final StockMapper stockMapper;

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

    @Override
    @Transactional
    public ProduitDTO createProduct(ProduitDTO produitDTO) {
        Produit produit = produitMapper.toEntity(produitDTO);
        produit.setDateAjout(LocalDateTime.now());

        Stock stock = stockMapper.toEntity(produitDTO.getStockDTO());
        stock.setQuantiteOriginal(stock.getQuantiteDisponible());

        produit.setStock(stock);
        stock.setProduit(produit);

        return produitMapper.toDTO(produitRepository.save(produit));
    }

    @Override
    public PagedResult<ProduitDTO> getPagedProduits(int pageNum, String filterBy) {
        Sort sort = Sort.by(filterBy).ascending();
        pageNum = pageNum <= 1 ? 0 : pageNum - 1;
        Pageable pageable = PageRequest.of(pageNum, 10, sort);
        Page<ProduitDTO> produitDTOs = produitRepository.findAll(pageable).map(produitMapper::toDTO);

        return new PagedResult<>(
                produitDTOs.getContent(),
                produitDTOs.getTotalElements(),
                produitDTOs.getNumber()+1,
                produitDTOs.getTotalPages(),
                produitDTOs.isFirst(),
                produitDTOs.isLast(),
                produitDTOs.hasNext(),
                produitDTOs.hasPrevious()
        );
    }
}
