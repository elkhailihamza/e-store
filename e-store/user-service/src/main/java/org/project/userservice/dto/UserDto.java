package org.project.userservice.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    @NotBlank
    private String nom;

    @NotBlank
    private String prenom;
    @Email
    private String email;

    @NotBlank
    private String password;
    private List<AddressRequestDto> addresses;
    private CartDto cart;
}
