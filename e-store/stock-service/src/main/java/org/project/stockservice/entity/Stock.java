package org.project.stockservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(nullable = false)
    private Produit produit;

    @Column(nullable = false)
    private int quantiteDisponible;

    @Column(nullable = false)
    private int quantiteOriginal;

    @Column(nullable = false)
    private int seuilMinimum;
}

