package com.spring.restaurant.controller.api;

import com.spring.restaurant.message.request.LoginForm;
import com.spring.restaurant.message.request.SignUpForm;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

import static com.spring.restaurant.util.Constants.APP_ROOT;

public interface AuthApi {

    @PostMapping(value = APP_ROOT + "/auth/signIn", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest);

    @PostMapping(value = APP_ROOT + "/auth/signUp", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest);

}
