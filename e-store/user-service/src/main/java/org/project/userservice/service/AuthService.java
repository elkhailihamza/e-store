package org.project.userservice.service;

import org.project.userservice.dto.LoginRequest;
import org.project.userservice.dto.RegisterRequest;
import org.project.userservice.entity.User;
import org.project.userservice.vm.AuthResponseVM;

public interface AuthService {
    String register(RegisterRequest request);
    AuthResponseVM login(LoginRequest request);
    User getAuthenticatedUser();

}
