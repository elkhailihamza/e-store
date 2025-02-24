package org.project.userservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemRequest {
    @NotNull(message = "L'identifiant du produit est obligatoire")
    private Long produitId;

    @Min(value = 1, message = "La quantité doit être au minimum de 1")
    private int quantite;
}
