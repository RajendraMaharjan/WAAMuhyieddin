package edu.miu.waalab.service.auth.impl;

import edu.miu.waalab.domain.auth.dto.request.LoginRequest;
import edu.miu.waalab.domain.auth.dto.request.RefreshTokenRequest;
import edu.miu.waalab.domain.auth.dto.response.LoginResponse;
import edu.miu.waalab.service.auth.AuthService;
import edu.miu.waalab.utilities.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {

        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(), loginRequest.getPassword()
            ));
        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException(ex.getMessage());
        } catch (Exception e) {
            throw new BadCredentialsException(e.getMessage());
        }


        final UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());
        final String accessToken = jwtUtil.generateToken(userDetails);
        final String refreshToken = jwtUtil.generateRefreshToken(loginRequest.getEmail());
        var loginResponse = new LoginResponse(accessToken, refreshToken);

        return loginResponse;

    }

    @Override
    public LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        boolean isRefreshTokenValid = jwtUtil.validateToken(refreshTokenRequest.getRefreshToken());

        if (isRefreshTokenValid) {
            var isAccessTokenExpired = jwtUtil.isTokenExpired(refreshTokenRequest.getAccessToken());
            if (isAccessTokenExpired) {



                System.out.println("Access token is expired.");
            } else {



                System.out.println("ACCESS TOKEN IS STILL VALID - NOT EXPIRED.");
            }

            final String accessToken = jwtUtil.doGenerateToken(jwtUtil.getSubject(refreshTokenRequest.getAccessToken()));
            var loginResponse = new LoginResponse(accessToken, refreshTokenRequest.getRefreshToken());

            return loginResponse;
        }
        return new LoginResponse();
    }
}
