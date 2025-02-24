package org.project.userservice.vm;
import lombok.*;
import org.project.userservice.dto.AddressRequestDto;

import java.util.List;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseVM {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private List<AddressVM> addresses;
    private String token;
}
