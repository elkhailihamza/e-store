package org.project.paymentservice.controller;

import org.project.paymentservice.dto.RemboursementDTO;
import org.project.paymentservice.service.RemboursementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/remboursements")
public class RemboursementController {

    private final RemboursementService remboursementService;

    public RemboursementController(RemboursementService remboursementService) {
        this.remboursementService = remboursementService;
    }

    @GetMapping
    public ResponseEntity<List<RemboursementDTO>> getAllRemboursements() {
        return ResponseEntity.ok(remboursementService.getAllRemboursements());
    }

    @PostMapping
    public ResponseEntity<RemboursementDTO> createRemboursement(@RequestBody RemboursementDTO remboursementDTO) {
        return ResponseEntity.ok(remboursementService.createRemboursement(remboursementDTO));
    }
}
