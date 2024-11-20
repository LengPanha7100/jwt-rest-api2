package com.example.demospring.jwtrestapi2.controller;
import com.example.demospring.jwtrestapi2.config.BeanConfig;
import com.example.demospring.jwtrestapi2.jwt.JwtService;
import com.example.demospring.jwtrestapi2.model.AppUser;
import com.example.demospring.jwtrestapi2.model.dto.request.AppUserRequest;
import com.example.demospring.jwtrestapi2.model.dto.request.AuthRequest;
import com.example.demospring.jwtrestapi2.model.dto.response.AppUserResponse;
import com.example.demospring.jwtrestapi2.model.dto.response.AuthResponse;
import com.example.demospring.jwtrestapi2.service.AppUserService;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final AppUserService appUserService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private void authenticate(String username, String password) throws Exception {
        try {
            UserDetails userApp = appUserService.loadUserByUsername(username);
            if (userApp == null) {
                throw new BadRequestException("Wrong Email");
            }
            if (!passwordEncoder.matches(password, userApp.getPassword())) {
                throw new BadRequestException("Wrong Password");
            }
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest authRequest) throws Exception {
        authenticate(authRequest.getEmail(), authRequest.getPassword());
        final UserDetails userDetails = appUserService.loadUserByUsername(authRequest.getEmail());
        final String token = jwtService.generateToken(userDetails);
        AuthResponse authResponse = new AuthResponse(token);
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register (@RequestBody AppUserRequest appUserRequest){
        appUserRequest.setPassword(bCryptPasswordEncoder.encode(appUserRequest.getPassword())); //encode password to make it not easy to read
        AppUser appUser = appUserService.register(appUserRequest);
        AppUserResponse appUserResponse = modelMapper.map(appUser,AppUserResponse.class); //map response
        return ResponseEntity.ok(appUserResponse);
    }
}