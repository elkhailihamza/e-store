package org.project.userservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.project.userservice.dto.LoginRequest;
import org.project.userservice.dto.RegisterRequest;
import org.project.userservice.entity.Address;
import org.project.userservice.entity.User;
import org.project.userservice.mapper.UserMapper;
import org.project.userservice.repository.AddressRepository;
import org.project.userservice.repository.UserRepository;
import org.project.userservice.security.JwtService;
import org.project.userservice.service.AuthService;
import org.project.userservice.shared.exception.EmailAlreadyExist;
import org.project.userservice.shared.exception.InvalidCredentialsException;
import org.project.userservice.vm.AuthResponseVM;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JwtService jwtService;
    private final AddressRepository addressRepository;


    @Override
    public String register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailAlreadyExist("Email already exists");
        }
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .prenom(request.getPrenom())
                .nom(request.getNom())
                .build();

        user = userRepository.save(user);
        System.out.println("Utilisateur enregistré : " + user.getId());

        if (request.getAddresses() != null && !request.getAddresses().isEmpty()) {
            User finalUser = user;
            List<Address> addresses = request.getAddresses().stream().map(addrReq -> {
                Address address = new Address();
                address.setRue(addrReq.getRue());
                address.setVille(addrReq.getVille());
                address.setCodePostal(addrReq.getCodePostal());
                address.setPays(addrReq.getPays());
                address.setUser(finalUser);
                return address;
            }).toList();

            addressRepository.saveAll(addresses);
        }

        return "User registered successfully";
    }

    @Override
    public AuthResponseVM login(LoginRequest loginRequest) {
        Optional<User> userOptional = userRepository.findByEmail(loginRequest.getEmail());
        if (userOptional.isEmpty()) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        User user = userOptional.get();
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password.");
        }

        String token = jwtService.generateToken(user, user.getId());
        AuthResponseVM authResponseVM = userMapper.toDto(user);
        authResponseVM.setToken(token);
        return authResponseVM;
    }
}
