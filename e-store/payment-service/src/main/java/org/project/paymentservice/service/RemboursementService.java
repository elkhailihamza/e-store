package org.project.paymentservice.service;

import org.project.paymentservice.dto.RemboursementDTO;
import org.project.paymentservice.entity.Payment;
import org.project.paymentservice.entity.Remboursement;
import org.project.paymentservice.entity.enums.PaymentStatus;
import org.project.paymentservice.mapper.RemboursementMapper;
import org.project.paymentservice.repository.PaymentRepository;
import org.project.paymentservice.repository.RemboursementRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RemboursementService {

    private final RemboursementRepository remboursementRepository;
    private final PaymentRepository paymentRepository;
    private final RemboursementMapper remboursementMapper;

    public RemboursementService(RemboursementRepository remboursementRepository,
                                PaymentRepository paymentRepository,
                                RemboursementMapper remboursementMapper) {
        this.remboursementRepository = remboursementRepository;
        this.paymentRepository = paymentRepository;
        this.remboursementMapper = remboursementMapper;
    }

    public List<RemboursementDTO> getAllRemboursements() {
        return remboursementRepository.findAll().stream()
                .map(remboursementMapper::toDTO)
                .collect(Collectors.toList());
    }

    public RemboursementDTO createRemboursement(RemboursementDTO remboursementDTO) {
        Optional<Payment> optionalPayment = paymentRepository.findById(remboursementDTO.getPaymentId());

        if (optionalPayment.isEmpty()) {
            throw new RuntimeException("Payment not found for ID: " + remboursementDTO.getPaymentId());
        }

        Payment payment = optionalPayment.get();

        if (!PaymentStatus.COMPLETED.equals(payment.getStatus())) {
            throw new RuntimeException("Refunds can only be issued for completed payments.");
        }

        if (!payment.getPaymentMethod().equals(remboursementDTO.getPaymentMethod())) {
            throw new RuntimeException("Refunds must be processed using the same payment method.");
        }

        Remboursement remboursement = remboursementMapper.toEntity(remboursementDTO);
        remboursement.setPayment(payment);
        remboursement.setDate(LocalDateTime.now());
        remboursement.setStatus("PENDING");

        Remboursement saved = remboursementRepository.save(remboursement);
        payment.setStatus(PaymentStatus.REFUNDED);
        paymentRepository.save(payment);

        return remboursementMapper.toDTO(saved);
    }
}
