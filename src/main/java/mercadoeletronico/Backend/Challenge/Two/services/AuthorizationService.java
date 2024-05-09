package mercadoeletronico.Backend.Challenge.Two.services;

import mercadoeletronico.Backend.Challenge.Two.domain.user.UserModel;
import mercadoeletronico.Backend.Challenge.Two.dtos.LoginDTO;
import mercadoeletronico.Backend.Challenge.Two.dtos.RegisterUserDTO;
import mercadoeletronico.Backend.Challenge.Two.exceptions.DuplicateCreationAttemptException;
import mercadoeletronico.Backend.Challenge.Two.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    public UserModel register(RegisterUserDTO registerDTO) throws DuplicateCreationAttemptException {
        if (userRepository.findByEmail(registerDTO.email).isPresent()) {
            throw new DuplicateCreationAttemptException(registerDTO.email,
                    "e-mail", "user");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password);

        UserModel newUser = new UserModel(registerDTO.email, encryptedPassword);

        return userRepository.save(newUser);
    }

    public String login(LoginDTO loginDTO){
        var userSaved = userRepository.findByEmail(loginDTO.email);

        if(userSaved.isEmpty())
            return "error-message";

        var usernamePassword = new UsernamePasswordAuthenticationToken(loginDTO.email, loginDTO.password);
        var auth = authenticationManager.authenticate(usernamePassword);

        return tokenService.generateToken((User) auth.getPrincipal());
    }

}
