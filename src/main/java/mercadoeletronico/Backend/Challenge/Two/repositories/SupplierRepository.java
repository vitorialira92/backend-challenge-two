package mercadoeletronico.Backend.Challenge.Two.repositories;

import mercadoeletronico.Backend.Challenge.Two.domain.supplier.Supplier;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SupplierRepository extends MongoRepository<Supplier, String> {
    Optional<Supplier> findByDocument(String document);
}
