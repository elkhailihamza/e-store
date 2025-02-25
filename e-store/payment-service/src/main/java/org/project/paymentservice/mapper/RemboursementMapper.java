package org.project.paymentservice.mapper;

import org.project.paymentservice.dto.RemboursementDTO;
import org.project.paymentservice.entity.Remboursement;
import org.springframework.stereotype.Component;

@Component
public class RemboursementMapper {

    public RemboursementDTO toDTO(Remboursement remboursement) {
        RemboursementDTO dto = new RemboursementDTO();
        dto.setId(remboursement.getId());
        dto.setPaymentId(remboursement.getPayment().getId());
        dto.setAmountRefunded(remboursement.getAmountRefunded());
        dto.setDate(remboursement.getDate());
        dto.setStatus(remboursement.getStatus());
        dto.setPaymentMethod(remboursement.getPaymentMethod());
        return dto;
    }

    public Remboursement toEntity(RemboursementDTO dto) {
        Remboursement remboursement = new Remboursement();
        remboursement.setAmountRefunded(dto.getAmountRefunded());
        remboursement.setDate(dto.getDate());
        remboursement.setStatus(dto.getStatus());
        remboursement.setPaymentMethod(dto.getPaymentMethod());
        return remboursement;
    }
}
