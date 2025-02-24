package org.project.userservice.vm;

import lombok.*;

import java.util.List;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartVM {
    private Long id;
    private UserVM user;
    private List<CartItemVm> items;
}
