package com.spring.restaurant.controller;

import com.spring.restaurant.controller.api.AuthApi;
import com.spring.restaurant.deo.RoleRepository;
import com.spring.restaurant.deo.UtilisateurRepository;
import com.spring.restaurant.enums.RoleName;
import com.spring.restaurant.message.request.LoginForm;
import com.spring.restaurant.message.request.SignUpForm;
import com.spring.restaurant.message.response.JwtResponse;
import com.spring.restaurant.message.response.ResponseMessage;
import com.spring.restaurant.model.Role;
import com.spring.restaurant.model.Utilisateur;
import com.spring.restaurant.security.jwt.JwtProvider;
import com.spring.restaurant.security.services.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AuthController implements AuthApi {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UtilisateurRepository userRepository;

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtProvider jwtProvider;

    @Override
    public ResponseEntity<?> authenticateUser(LoginForm loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        //    UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        UserPrinciple userDetails = (UserPrinciple) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities()
                .stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        Optional<Utilisateur> optionalUtilisateur = userRepository.findById(userDetails.getId());
        Utilisateur utilisateur = optionalUtilisateur.get();


        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @Override
    public ResponseEntity<?> registerUser(SignUpForm signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        Utilisateur user = new Utilisateur(signUpRequest.getName(),
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        // Set<String> strRoles = signUpRequest.getRole();
        String[] roleArr = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (roleArr == null) {
            roles.add(roleRepository.findByName(RoleName.ROLE_VENDEUR).get());
        }

        for (String role : roleArr) {
            switch (role.toLowerCase()) {
                case "admin":
                    roles.add(roleRepository.findByName(RoleName.ROLE_ADMIN).get());
                    break;

                case "manager":
                    roles.add(roleRepository.findByName(RoleName.ROLE_MANAGER).get());
                    break;

                case "vendeur":
                    roles.add(roleRepository.findByName(RoleName.ROLE_VENDEUR).get());
                    break;

                default:
                    return ResponseEntity.badRequest().body("Specified role not found");

            }
        }

        user.setRoles(roles);

        user.setActive(true);

        userRepository.save(user);

        return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.CREATED);

    }
}
