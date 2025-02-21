package org.project.userservice.entity;
import jakarta.persistence.*;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.*;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String rue;

    @NotBlank
    private String ville;

    @NotBlank
    private String codePostal;

    @NotBlank
    private String pays;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
