package org.project.paymentservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.project.paymentservice.entity.enums.PaymentMethod;
import org.project.paymentservice.entity.enums.PaymentStatus;

import java.time.LocalDateTime;

public class PaymentDTO {

    private Double amount;
    private String orderReference;
    private PaymentMethod paymentMethod;
    private PaymentStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime paymentDate;


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
