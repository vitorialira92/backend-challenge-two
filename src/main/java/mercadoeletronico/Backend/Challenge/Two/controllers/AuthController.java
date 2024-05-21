package mercadoeletronico.Backend.Challenge.Two.controllers;

import jakarta.validation.Valid;
import mercadoeletronico.Backend.Challenge.Two.domain.user.UserModel;
import mercadoeletronico.Backend.Challenge.Two.dtos.LoginResponseDTO;
import mercadoeletronico.Backend.Challenge.Two.services.AuthorizationService;
import mercadoeletronico.Backend.Challenge.Two.dtos.LoginDTO;
import mercadoeletronico.Backend.Challenge.Two.dtos.RegisterUserDTO;
import mercadoeletronico.Backend.Challenge.Two.exceptions.DuplicateCreationAttemptException;
import mercadoeletronico.Backend.Challenge.Two.services.JwtTokenProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller()
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthorizationService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.email,
                        loginDTO.password
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = tokenProvider.generateToken((UserModel) userDetails);

        String userId = ((UserModel) userDetails).getId();

        return ResponseEntity.ok(new LoginResponseDTO(token, userId, "home-page"));
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterUserDTO registerDTO, Model model) throws DuplicateCreationAttemptException {
        authService.register(registerDTO);
        return "login";
    }
}
