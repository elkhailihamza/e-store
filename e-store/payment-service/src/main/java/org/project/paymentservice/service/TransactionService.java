package org.project.paymentservice.service;

import org.project.paymentservice.dto.TransactionDTO;
import org.project.paymentservice.entity.Payment;
import org.project.paymentservice.entity.Transaction;
import org.project.paymentservice.mapper.TransactionMapper;
import org.project.paymentservice.repository.PaymentRepository;
import org.project.paymentservice.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final PaymentRepository paymentRepository;
    private final TransactionMapper transactionMapper;

    public TransactionService(TransactionRepository transactionRepository, PaymentRepository paymentRepository, TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.paymentRepository = paymentRepository;
        this.transactionMapper = transactionMapper;
    }

    public List<TransactionDTO> getAllTransactions() {
        return transactionRepository.findAll().stream()
                .map(transactionMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<TransactionDTO> getTransactionsByPaymentId(Long paymentId) {
        return transactionRepository.findByPaymentId(paymentId).stream()
                .map(transactionMapper::toDTO)
                .collect(Collectors.toList());
    }

    public TransactionDTO createTransaction(TransactionDTO transactionDTO) {
        Long paymentId = transactionDTO.getPaymentId();
        String reference = transactionDTO.getReference();
        String status = transactionDTO.getStatus();

        Optional<Payment> optionalPayment = paymentRepository.findById(paymentId);

        if (optionalPayment.isEmpty()) {
            throw new RuntimeException("Payment not found for ID: " + paymentId);
        }

        Payment payment = optionalPayment.get();
        Transaction transaction = new Transaction(payment, reference, status, LocalDateTime.now());
        Transaction savedTransaction = transactionRepository.save(transaction);

        return transactionMapper.toDTO(savedTransaction);
    }

}
