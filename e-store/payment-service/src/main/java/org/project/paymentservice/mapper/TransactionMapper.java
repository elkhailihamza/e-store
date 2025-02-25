package org.project.paymentservice.mapper;

import org.project.paymentservice.dto.TransactionDTO;
import org.project.paymentservice.entity.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    public TransactionDTO toDTO(Transaction transaction) {
        TransactionDTO dto = new TransactionDTO();
        dto.setId(transaction.getId());
        dto.setPaymentId(transaction.getPayment().getId());
        dto.setReference(transaction.getReference());
        dto.setStatus(transaction.getStatus());
        dto.setDateTransaction(transaction.getDateTransaction());
        return dto;
    }

    public Transaction toEntity(TransactionDTO transactionDTO) {
        Transaction transaction = new Transaction();
        transaction.setReference(transactionDTO.getReference());
        transaction.setStatus(transactionDTO.getStatus());
        transaction.setDateTransaction(transactionDTO.getDateTransaction());
        return transaction;
    }
}
