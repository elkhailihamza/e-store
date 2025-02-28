package org.project.paymentservice.service;

import org.project.paymentservice.dto.PaymentDTO;
import org.project.paymentservice.entity.Payment;
import org.project.paymentservice.entity.enums.PaymentMethod;
import org.project.paymentservice.mapper.PaymentMapper;
import org.project.paymentservice.paypal.PayPalIntegrationService;
import org.project.paymentservice.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final PayPalIntegrationService payPalIntegrationService;

    public PaymentService(
            PaymentRepository paymentRepository,
            PaymentMapper paymentMapper,
            PayPalIntegrationService payPalIntegrationService
    ) {
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
        this.payPalIntegrationService = payPalIntegrationService;
    }

    public List<PaymentDTO> getAllPayments() {
        return paymentRepository.findAll().stream()
                .map(paymentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PaymentDTO createPayment(PaymentDTO paymentDTO) {
        if (paymentDTO.getPaymentDate() == null) {
            paymentDTO.setPaymentDate(LocalDateTime.now());
        }

        if (paymentDTO.getPaymentMethod() == PaymentMethod.PAYPAL) {
            payPalIntegrationService.createPayPalPayment(paymentDTO.getAmount(), "USD");
        }

        Payment payment = paymentMapper.toEntity(paymentDTO);
        Payment saved = paymentRepository.save(payment);

        return paymentMapper.toDTO(saved);
    }
}
