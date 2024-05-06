package mercadoeletronico.Backend.Challenge.Two.repositories;

import mercadoeletronico.Backend.Challenge.Two.domain.supplier.Supplier;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SupplierRepository extends MongoRepository<Supplier, String> {
}
