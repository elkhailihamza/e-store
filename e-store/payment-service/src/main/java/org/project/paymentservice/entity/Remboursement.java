package org.project.paymentservice.entity;

import jakarta.persistence.*;
import org.project.paymentservice.entity.enums.PaymentMethod;
import java.time.LocalDateTime;

@Entity
public class Remboursement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "payment_id", nullable = false)
    private Payment payment;

    private Double amountRefunded;
    private String reason;
    private String status; // PENDING, APPROVED, REJECTED

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Column(name = "refund_date")
    private LocalDateTime date;


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Payment getPayment() { return payment; }
    public void setPayment(Payment payment) { this.payment = payment; }

    public Double getAmountRefunded() { return amountRefunded; }
    public void setAmountRefunded(Double amountRefunded) { this.amountRefunded = amountRefunded; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public PaymentMethod getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(PaymentMethod paymentMethod) { this.paymentMethod = paymentMethod; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }
}
