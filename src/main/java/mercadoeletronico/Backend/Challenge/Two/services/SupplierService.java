package mercadoeletronico.Backend.Challenge.Two.services;

import mercadoeletronico.Backend.Challenge.Two.domain.supplier.*;
import mercadoeletronico.Backend.Challenge.Two.dtos.SupplierCreationDTO;
import mercadoeletronico.Backend.Challenge.Two.dtos.SupplierDTO;
import mercadoeletronico.Backend.Challenge.Two.dtos.SupplierSimpleDTO;
import mercadoeletronico.Backend.Challenge.Two.dtos.SupplierUpdateDTO;
import mercadoeletronico.Backend.Challenge.Two.exceptions.DuplicateCreationAttemptException;
import mercadoeletronico.Backend.Challenge.Two.exceptions.ResourceNotFoundException;
import mercadoeletronico.Backend.Challenge.Two.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository repository;

    public List<SupplierSimpleDTO> getAll(String userId) {
        List<SupplierSimpleDTO> suppliers = new ArrayList<>();

        for(Supplier supplier : repository.findAllByUserId(userId)) {
            String document = (supplier.getDocumentType() == DocumentType.CPF ? "CPF " : "CNPJ ")
                    + supplier.getDocument();
            suppliers.add(new SupplierSimpleDTO(supplier.getId(),
                    supplier.getName(), document));
        }
        return suppliers;
    }

    public SupplierDTO getById(String id) throws ResourceNotFoundException {

        Optional<Supplier> optionalSupplier = repository.findById(id);
        if(optionalSupplier.isPresent()){
            var supplier = optionalSupplier.get();

            return new SupplierDTO(supplier.getName(), supplier.getType(), supplier.getDocument(), supplier.getMainContact().getName(),
                    supplier.getMainContact().getEmail(), supplier.getPhoneNumbers(), supplier.getActivitiesDescription(),
                    supplier.getAddress().getZipcode(), supplier.getAddress().getStreet(), supplier.getAddress().getNumber(),
                    supplier.getAddress().getComplement(), supplier.getAddress().getNeighborhood(),
                    supplier.getAddress().getCity(), supplier.getAddress().getState());
        }


        throw new ResourceNotFoundException("Supplier");
    }

    public Supplier createSupplier(SupplierCreationDTO supplierDTO, String userId) throws DuplicateCreationAttemptException{

        Optional<Supplier> opt = repository.findByUserIdAndDocument(userId, supplierDTO.document);
        if(opt.isPresent())
                throw new DuplicateCreationAttemptException(opt.get().getDocument(),
                        opt.get().getDocumentType().name(), "supplier");
        Address address = new Address(supplierDTO.zipCode, supplierDTO.street, supplierDTO.number, supplierDTO.complement,
                supplierDTO.neighborhood, supplierDTO.city, State.valueOf(String.valueOf(supplierDTO.state)));
        Supplier supplier = new Supplier(
                userId,
                supplierDTO.name,
                supplierDTO.type,
                supplierDTO.document,
                new SupplierMainContact(supplierDTO.contactName, supplierDTO.contactEmail),
                address,
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

    public Supplier updateSupplier(String id, SupplierUpdateDTO supplierDTO) throws ResourceNotFoundException {
        Optional<Supplier> optionalSupplier = repository.findById(id);

        if(optionalSupplier.isPresent()) {
            Supplier supplier = optionalSupplier.get();

            supplier.setActivitiesDescription(supplierDTO.activitiesDescription);

            SupplierMainContact contact = supplier.getMainContact();
            contact.setName(supplierDTO.contactName);
            contact.setEmail(supplierDTO.contactEmail);
            supplier.setMainContact(contact);

            supplier.setPhoneNumbers(supplierDTO.phoneNumbers);

            Address address = supplier.getAddress();
            address.update(supplierDTO.zipCode, supplierDTO.street, supplierDTO.number, supplierDTO.complement,
                    supplierDTO.neighborhood, supplierDTO.city, State.valueOf(String.valueOf(supplierDTO.state)));
            supplier.setAddress(address);

            repository.save(supplier);

            return supplier;
        }
        throw new ResourceNotFoundException("Supplier");
    }

    public void deleteSupplier(String id) throws ResourceNotFoundException {
        Optional<Supplier> optionalSupplier = repository.findById(id);

        if(optionalSupplier.isPresent())
            repository.delete(optionalSupplier.get());
        else
            throw new ResourceNotFoundException("Supplier");
    }
}
