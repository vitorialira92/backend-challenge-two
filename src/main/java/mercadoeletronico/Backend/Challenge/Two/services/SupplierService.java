package mercadoeletronico.Backend.Challenge.Two.services;

import mercadoeletronico.Backend.Challenge.Two.domain.supplier.Supplier;
import mercadoeletronico.Backend.Challenge.Two.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository repository;

    public List<Supplier> getAll() {
        return repository.findAll();
    }

    public Optional<Supplier> getById(Long id) {
        return repository.findById(id);
    }
}
