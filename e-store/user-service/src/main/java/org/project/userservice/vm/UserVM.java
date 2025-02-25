package org.project.userservice.vm;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserVM {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private List<AddressVM> addresses;
    private CartVM cart;
}