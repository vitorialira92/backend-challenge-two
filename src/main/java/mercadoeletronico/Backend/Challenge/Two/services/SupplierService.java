package mercadoeletronico.Backend.Challenge.Two.services;

import mercadoeletronico.Backend.Challenge.Two.domain.supplier.Supplier;
import mercadoeletronico.Backend.Challenge.Two.domain.supplier.SupplierMainContact;
import mercadoeletronico.Backend.Challenge.Two.dtos.SupplierCreationDTO;
import mercadoeletronico.Backend.Challenge.Two.dtos.SupplierUpdateDTO;
import mercadoeletronico.Backend.Challenge.Two.exceptions.DuplicateCreationAttemptException;
import mercadoeletronico.Backend.Challenge.Two.exceptions.ResourceNotFoundException;
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

    public Optional<Supplier> getById(String id) throws ResourceNotFoundException {

        Optional<Supplier> supplier = repository.findById(id);
        if(supplier.isPresent())
            return supplier;


        throw new ResourceNotFoundException("Supplier");
    }

    public Supplier createSupplier(SupplierCreationDTO supplierDTO) throws DuplicateCreationAttemptException{

        Optional<Supplier> opt = repository.findByDocument(supplierDTO.document);
        if(opt.isPresent())
                throw new DuplicateCreationAttemptException(opt.get().getDocument(),
                        opt.get().getDocumentType().name(), "supplier");

        Supplier supplier = new Supplier(
                supplierDTO.name,
                supplierDTO.type,
                supplierDTO.document,
                new SupplierMainContact(supplierDTO.contactName, supplierDTO.contactEmail),
                supplierDTO.phoneNumbers,
                supplierDTO.activitiesDescription
        );
        supplier = repository.save(supplier);

        SupplierMainContact contact = supplier.getMainContact();
        contact.setSupplierId(supplier.getId());
        supplier.setMainContact(contact);

        supplier = repository.save(supplier);

        return supplier;
    }

    public Optional<Supplier> updateSupplier(String id, SupplierUpdateDTO supplierDTO) throws ResourceNotFoundException {
        Optional<Supplier> optionalSupplier = repository.findById(id);

        if(optionalSupplier.isPresent()) {
            Supplier supplier = optionalSupplier.get();

            supplier.setActivitiesDescription(supplierDTO.activitiesDescription);

            SupplierMainContact contact = supplier.getMainContact();
            contact.setName(supplierDTO.contactName);
            contact.setEmail(supplierDTO.contactEmail);
            supplier.setMainContact(contact);

            supplier.setPhoneNumbers(supplierDTO.phoneNumbers);

            repository.save(supplier);

            return Optional.of(supplier);
        }
        throw new ResourceNotFoundException("Supplier");
    }
    public void deleteSupplier(String id) throws ResourceNotFoundException {
        Optional<Supplier> optionalSupplier = repository.findById(id);

        optionalSupplier.ifPresent(supplier -> repository.delete(supplier));

        throw new ResourceNotFoundException("Supplier");
    }
}
