package org.project.paymentservice.entity;

import jakarta.persistence.*;
import org.project.paymentservice.entity.enums.PaymentMethod;
import org.project.paymentservice.entity.enums.PaymentStatus;

import java.time.LocalDateTime;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;
    private String orderReference;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;


    public Long getId() {
        return id;
    }
    public void setId(Long id) { this.id = id; }

    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) { this.amount = amount; }

    public String getOrderReference() {
        return orderReference;
    }
    public void setOrderReference(String orderReference) { this.orderReference = orderReference; }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(PaymentMethod paymentMethod) { this.paymentMethod = paymentMethod; }



    public PaymentStatus getStatus() { return status; }
    public void setStatus(PaymentStatus status) { this.status = status; }


    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }
    public void setPaymentDate(LocalDateTime paymentDate) { this.paymentDate = paymentDate; }
}
