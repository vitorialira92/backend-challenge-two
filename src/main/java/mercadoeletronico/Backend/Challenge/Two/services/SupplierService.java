package mercadoeletronico.Backend.Challenge.Two.services;

import mercadoeletronico.Backend.Challenge.Two.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository repository;
}
