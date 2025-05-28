package com.pngo.crudProj.controller;


import com.nimbusds.jose.JOSEException;
import com.pngo.crudProj.dto.request.ApiResponse;
import com.pngo.crudProj.dto.request.AuthenicationRequest;
import com.pngo.crudProj.dto.request.IntrospectRequest;
import com.pngo.crudProj.dto.response.AuthenicationResponse;
import com.pngo.crudProj.dto.response.IntrospectResponse;
import com.pngo.crudProj.services.AuthenicationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenicationController {

    AuthenicationService authenicationService;

    @PostMapping("/login")
    ApiResponse<AuthenicationResponse> login(@RequestBody AuthenicationRequest request) {
        AuthenicationResponse result = authenicationService.authentication(request);
        return ApiResponse.<AuthenicationResponse>builder()
                .message("Login successful")
                .data(result)
                .build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest request) throws JOSEException, ParseException {
        IntrospectResponse result = authenicationService.introspectRequest(request);

        return ApiResponse.<IntrospectResponse>builder()
                .data(result)
                .build();
    }
}
