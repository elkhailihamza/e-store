package org.project.paymentservice.dto;

import java.time.LocalDateTime;

public class TransactionDTO {

    private Long id;
    private Long paymentId;
    private String reference;
    private String status;
    private LocalDateTime dateTransaction;

    public TransactionDTO() {}

    public TransactionDTO(Long id, Long paymentId, String reference, String status, LocalDateTime dateTransaction) {
        this.id = id;
        this.paymentId = paymentId;
        this.reference = reference;
        this.status = status;
        this.dateTransaction = dateTransaction;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getPaymentId() { return paymentId; }
    public void setPaymentId(Long paymentId) { this.paymentId = paymentId; }

    public String getReference() { return reference; }
    public void setReference(String reference) { this.reference = reference; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getDateTransaction() { return dateTransaction; }
    public void setDateTransaction(LocalDateTime dateTransaction) { this.dateTransaction = dateTransaction; }
}
