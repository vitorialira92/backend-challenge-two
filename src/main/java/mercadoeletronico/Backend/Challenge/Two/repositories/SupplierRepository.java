package mercadoeletronico.Backend.Challenge.Two.repositories;

import mercadoeletronico.Backend.Challenge.Two.domain.supplier.Supplier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierRepository extends MongoRepository<Supplier, String> {
    @Query("{'userId': ?0, 'document': ?1}")
    Optional<Supplier> findByUserIdAndDocument(String userId, String document);
    List<Supplier> findAllByUserId(String userId);

}
