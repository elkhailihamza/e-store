package org.project.paymentservice.dto;

import org.project.paymentservice.entity.enums.PaymentMethod;
import java.time.LocalDateTime;

public class RemboursementDTO {

    private Long id;
    private Long paymentId;
    private Double amountRefunded;
    private String reason;
    private String status;
    private PaymentMethod paymentMethod;
    private LocalDateTime date;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getPaymentId() { return paymentId; }
    public void setPaymentId(Long paymentId) { this.paymentId = paymentId; }

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
