package com.ogutcenali.controller;

import com.ogutcenali.dto.request.CreateAuthRequestDto;
import com.ogutcenali.dto.request.LoginRequestDto;
import com.ogutcenali.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.ogutcenali.constant.RestEndPoints.*;

@RestController
@RequestMapping(AUTH)   //api/v1/auth
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(SAVE)
    public ResponseEntity<Boolean> createAuth(@RequestBody @Valid CreateAuthRequestDto createAuthRequestDto) {
        return ResponseEntity.ok(authService.createAuth(createAuthRequestDto));
    }

    @PostMapping(LOGIN)
    public ResponseEntity<String> doLogin(@RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(authService.doLogin(loginRequestDto));
    }

    @PostMapping(ACTIVATE+"/{id}")
    public ResponseEntity<Boolean> activateAuthProfile(@PathVariable Long id){
        return ResponseEntity.ok(authService.activateAuth(id));
    }
}
