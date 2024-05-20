package mercadoeletronico.Backend.Challenge.Two.repositories;

import mercadoeletronico.Backend.Challenge.Two.domain.user.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserModel, String> {
    Optional<UserModel> findByEmail(String email);
}
