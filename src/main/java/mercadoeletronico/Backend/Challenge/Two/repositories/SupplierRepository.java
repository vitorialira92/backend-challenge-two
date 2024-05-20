package mercadoeletronico.Backend.Challenge.Two.repositories;

import mercadoeletronico.Backend.Challenge.Two.domain.supplier.Supplier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierRepository extends MongoRepository<Supplier, String> {
    Optional<Supplier> findByDocument(String document);
    List<Supplier> findAllByUserId(String userId);

}
