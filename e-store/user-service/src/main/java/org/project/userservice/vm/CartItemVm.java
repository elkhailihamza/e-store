package org.project.userservice.vm;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemVm {
    private Long produitId;
    private int quantite;
}
