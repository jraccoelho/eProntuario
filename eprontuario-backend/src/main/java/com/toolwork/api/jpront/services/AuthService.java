package com.toolwork.api.jpront.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.toolwork.api.jpront.configs.ObjectMapperUtil;
import com.toolwork.api.jpront.domains.JwtService;
import com.toolwork.api.jpront.domains.Role;
import com.toolwork.api.jpront.domains.User;
import com.toolwork.api.jpront.dtos.AuthRequest;
import com.toolwork.api.jpront.dtos.AuthResponse;
import com.toolwork.api.jpront.dtos.UserRequest;
import com.toolwork.api.jpront.dtos.UserResponse;
import com.toolwork.api.jpront.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

        private final UserRepository userRepo;
        private final PasswordEncoder pwdEnc;
        private final JwtService jwtSvc;
        private final AuthenticationManager authMgr;

        public AuthResponse authenticate(AuthRequest request) {
                authMgr.authenticate(
                                new UsernamePasswordAuthenticationToken(
                                                request.getUserName(),
                                                request.getPassword()));

                var user = userRepo.findById(request.getUserName())
                                .orElseThrow(() -> new UsernameNotFoundException("User not found."));

                var jwtToken = jwtSvc.generateToken(user);

                return AuthResponse.builder()
                                .token(jwtToken)
                                .build();
        }

        public AuthResponse register(UserRequest request) {
                var user = User.builder()
                                .userName(request.getUserId())
                                .fullName(request.getUserFullName())
                                .createdBy(request.getRequestor())
                                .createdDate(LocalDate.now())
                                .userActive(true)
                                .userPassword(pwdEnc.encode(request.getUserPassword()))
                                .role(Role.BASIC_USER)
                                .build();

                userRepo.save(user);
                var jwtToken = jwtSvc.generateToken(user);

                return AuthResponse.builder()
                                .token(jwtToken)
                                .build();
        }

        public List<UserResponse> getAllUsers() {
                return ObjectMapperUtil.mapAll(userRepo.findAll(), UserResponse.class);
        }
}
