package org.project.userservice.vm;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressVM {
    private Long id;
    private String rue;
    private String ville;
    private String codePostal;
    private String pays;

}
