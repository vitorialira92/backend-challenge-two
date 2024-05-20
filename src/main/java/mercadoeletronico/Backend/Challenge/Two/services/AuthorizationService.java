package mercadoeletronico.Backend.Challenge.Two.services;

import mercadoeletronico.Backend.Challenge.Two.domain.user.UserModel;
import mercadoeletronico.Backend.Challenge.Two.dtos.RegisterUserDTO;
import mercadoeletronico.Backend.Challenge.Two.exceptions.DuplicateCreationAttemptException;
import mercadoeletronico.Backend.Challenge.Two.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {
    @Autowired
    private UserRepository userRepository;

    public UserModel register(RegisterUserDTO registerDTO) throws DuplicateCreationAttemptException {
        if (userRepository.findByEmail(registerDTO.email).isPresent()) {
            throw new DuplicateCreationAttemptException(registerDTO.email,
                    "e-mail", "user");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password);

        UserModel newUser = new UserModel(registerDTO.email, encryptedPassword);

        return userRepository.save(newUser);
    }
}
