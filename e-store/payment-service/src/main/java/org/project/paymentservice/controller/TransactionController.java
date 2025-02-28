package org.project.paymentservice.controller;

import org.project.paymentservice.dto.TransactionDTO;
import org.project.paymentservice.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<List<TransactionDTO>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    @GetMapping("/payment/{paymentId}")
    public ResponseEntity<List<TransactionDTO>> getTransactionsByPaymentId(@PathVariable Long paymentId) {
        return ResponseEntity.ok(transactionService.getTransactionsByPaymentId(paymentId));
    }

    @PostMapping
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody TransactionDTO transactionDTO) {
        return ResponseEntity.ok(transactionService.createTransaction( transactionDTO));
    }

}
