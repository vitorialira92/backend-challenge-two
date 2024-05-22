package mercadoeletronico.Backend.Challenge.Two.services;

import jakarta.validation.constraints.NotNull;
import mercadoeletronico.Backend.Challenge.Two.domain.supplier.*;
import mercadoeletronico.Backend.Challenge.Two.dtos.SupplierCreationDTO;
import mercadoeletronico.Backend.Challenge.Two.exceptions.DuplicateCreationAttemptException;
import mercadoeletronico.Backend.Challenge.Two.repositories.SupplierRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.hamcrest.CoreMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalToObject;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class SupplierServiceTest {
    @Mock
    private SupplierRepository supplierRepository;
    @InjectMocks
    private SupplierService supplierService;

    @Test
    public void createSupplierShouldThrowExceptionWhenAttemptToCreateSupplierWithTheSameDocument()
            throws DuplicateCreationAttemptException {
        when(supplierRepository.findByUserIdAndDocument("123abc", "47.960.950/0001-21"))
                .thenReturn(Optional.of(getStandardSupplier()));

        SupplierCreationDTO supplierDTO = new SupplierCreationDTO();
        supplierDTO.document = "47.960.950/0001-21";

        assertThrows(DuplicateCreationAttemptException.class, () -> {
            supplierService.createSupplier(supplierDTO, "123abc");
        });
    }

    @Test
    public void createSupplierShouldReturnTheCreatedSupplier() throws DuplicateCreationAttemptException {
        when(supplierRepository.findByUserIdAndDocument("123abc", "47.960.950/0001-21"))
                .thenReturn(Optional.empty());
        when(supplierRepository.save(any(Supplier.class))).thenReturn(getStandardSupplier());

        SupplierCreationDTO supplierDTO = getStandardSupplierCreationDTO();
        Supplier returnedSupplier = supplierService.createSupplier(supplierDTO, "123abc");
        assertEquals(getStandardSupplier(), returnedSupplier, "Expected supplier to match the created one");
    }


    private static Supplier getStandardSupplier(){
        SupplierMainContact contact = new SupplierMainContact("Alessandro", "alessandro@magazineluiza.com.br");
        contact.setSupplierId("124535fdvas2");
        Supplier supplier = new Supplier("123abc", "Magazine Luiza", SupplierType.LEGAL_ENTITY, "47.960.950/0001-21",
                contact,
                new Address("14.403-471", "Rua Arnulfo de Lima", "2385",
                "", "Vila Santa Cruz", "Franca", State.SP), new ArrayList<>(),
                "Marketplace");
        supplier.setId("124535fdvas2");
        return supplier;
    }

    private static SupplierCreationDTO getStandardSupplierCreationDTO(){
        SupplierCreationDTO supplierDTO = new SupplierCreationDTO();
        supplierDTO.name = "Magazine Luiza";
        supplierDTO.type = SupplierType.LEGAL_ENTITY;
        supplierDTO.document ="47.960.950/0001-21";
        supplierDTO.contactName = "Alessandro";
        supplierDTO.contactEmail = "alessandro@magazineluiza.com.br";
        supplierDTO.phoneNumbers = new ArrayList<>();
        supplierDTO.activitiesDescription = "Marketplace";
        supplierDTO.zipCode = "14.403-471";
        supplierDTO.street = "Rua Arnulfo de Lima";
        supplierDTO.number = "2385";
        supplierDTO.complement = "";
        supplierDTO.neighborhood = "Vila Santa Cruz";
        supplierDTO.city = "Franca";
        supplierDTO.state =  State.SP;
        return supplierDTO;
    }
}
