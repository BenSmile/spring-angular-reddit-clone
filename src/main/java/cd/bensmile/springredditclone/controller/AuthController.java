package cd.bensmile.springredditclone.controller;

import cd.bensmile.springredditclone.dto.AuthenticationResponse;
import cd.bensmile.springredditclone.dto.LoginRequest;
import cd.bensmile.springredditclone.dto.RegisterRequest;
import cd.bensmile.springredditclone.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {


    private AuthService authService;


    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody RegisterRequest registerRequest) {
        authService.signup(registerRequest);
        return new ResponseEntity<>("User Registration successful", HttpStatus.OK);
    }


    @GetMapping("/accountVerification/{token}")
    public ResponseEntity<?> accountVerification(@PathVariable String token) {
        System.out.println("token = " + token);
        authService.verifyToken(token);
        return new ResponseEntity<>("Account activated successfully", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        System.out.println("loginRequest = " + loginRequest.getUsername());
        AuthenticationResponse login = authService.login(loginRequest);
        System.out.println("=== loginRequest = " + loginRequest.getUsername());
        return new ResponseEntity<AuthenticationResponse>(login, HttpStatus.OK);
    }
}
