package org.project.paymentservice.repository;

import org.project.paymentservice.entity.Remboursement;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RemboursementRepository extends JpaRepository<Remboursement, Long> {
    List<Remboursement> findByPaymentId(Long paymentId);
}
