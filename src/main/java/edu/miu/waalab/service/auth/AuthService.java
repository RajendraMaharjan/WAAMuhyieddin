package edu.miu.waalab.service.auth;

import edu.miu.waalab.domain.auth.dto.request.LoginRequest;
import edu.miu.waalab.domain.auth.dto.request.RefreshTokenRequest;
import edu.miu.waalab.domain.auth.dto.response.LoginResponse;
import org.springframework.stereotype.Service;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);
    LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

}
