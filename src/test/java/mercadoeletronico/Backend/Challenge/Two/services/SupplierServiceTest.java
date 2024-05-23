package mercadoeletronico.Backend.Challenge.Two.services;

import mercadoeletronico.Backend.Challenge.Two.domain.supplier.*;
import mercadoeletronico.Backend.Challenge.Two.dtos.SupplierCreationDTO;
import mercadoeletronico.Backend.Challenge.Two.dtos.SupplierDTO;
import mercadoeletronico.Backend.Challenge.Two.dtos.SupplierSimpleDTO;
import mercadoeletronico.Backend.Challenge.Two.dtos.SupplierUpdateDTO;
import mercadoeletronico.Backend.Challenge.Two.exceptions.DuplicateCreationAttemptException;
import mercadoeletronico.Backend.Challenge.Two.exceptions.ResourceNotFoundException;
import mercadoeletronico.Backend.Challenge.Two.repositories.SupplierRepository;
import mercadoeletronico.Backend.Challenge.Two.services.Utils.SupplierForUserId;
import mercadoeletronico.Backend.Challenge.Two.services.Utils.SupplierServiceTestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class SupplierServiceTest {
    @Mock
    private SupplierRepository supplierRepository;
    @InjectMocks
    private SupplierService supplierService;

    @Test
    public void getAllShouldReturnAnEmptyListWhenThereAreNoSuppliers(){
        when(supplierRepository.findAllByUserId(any(String.class))).thenReturn(new ArrayList<>());

        List<SupplierSimpleDTO> returnedList = supplierService.getAll("fakeUserId");

        assertEquals(returnedList.size(), 0, "The list should be empty");

        verify(supplierRepository).findAllByUserId("fakeUserId");
    }

    @ParameterizedTest
    @MethodSource("getSupplierListForUser")
    public void getAllShouldReturnAListWhenThereAreSuppliers(SupplierForUserId supplier){
        when(supplierRepository.findAllByUserId(supplier.userId)).thenReturn(supplier.suppliers);

        List<SupplierSimpleDTO> returnedList = supplierService.getAll(supplier.userId);

        assertEquals(returnedList.size(), supplier.suppliers.size(), "Both lists should have the same size");

        for (int i = 0; i < returnedList.size(); i++) {
            SupplierSimpleDTO dto = returnedList.get(i);
            Supplier original = supplier.suppliers.get(i);

            assertEquals(original.getName(), dto.getName(), "Names should match");
            String expectedDocument = (original.getDocumentType() == DocumentType.CPF ? "CPF " : "CNPJ ") + original.getDocument();
            assertEquals(expectedDocument, dto.getDocument(), "Documents should match");
        }

        verify(supplierRepository).findAllByUserId(supplier.userId);
    }

    @Test
    public void getByIdShouldThrowExceptionWhenSupplierDoesntExist(){
        when(supplierRepository.findById("123abc"))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            supplierService.getById("123abc");
        });

        verify(supplierRepository).findById("123abc");
    }

    @Test
    public void getByIdShouldReturnSupplierWhenSupplierExists() throws ResourceNotFoundException {
        Supplier expectedSupplier = SupplierServiceTestData.getStandardSupplier();

        when(supplierRepository.findById("124535fdvas2"))
                .thenReturn(Optional.of(expectedSupplier));

        assertDoesNotThrow(() -> {
            Optional<SupplierDTO> result = supplierService.getById("124535fdvas2");

            assertTrue(result.isPresent(), "Supplier should be found");

            SupplierDTO supplierDTO = result.get();

            assertEquals(expectedSupplier.getName(), supplierDTO.name, "Names should match");
            assertEquals(expectedSupplier.getType(), supplierDTO.type, "Types should match");
            assertEquals(expectedSupplier.getDocument(), supplierDTO.document, "Documents should match");
            assertEquals(expectedSupplier.getMainContact().getName(), supplierDTO.contactName, "Contact names should match");
            assertEquals(expectedSupplier.getMainContact().getEmail(), supplierDTO.contactEmail, "Contact emails should match");
            assertEquals(expectedSupplier.getPhoneNumbers().size(), supplierDTO.phoneNumbers.size(), "Phone numbers quantity should match");
            assertEquals(expectedSupplier.getActivitiesDescription(), supplierDTO.activitiesDescription, "Activities descriptions should match");
            assertEquals(expectedSupplier.getAddress().getZipcode(), supplierDTO.zipCode, "Zip codes should match");
            assertEquals(expectedSupplier.getAddress().getStreet(), supplierDTO.street, "Streets should match");
            assertEquals(expectedSupplier.getAddress().getNumber(), supplierDTO.number, "Numbers should match");
            assertEquals(expectedSupplier.getAddress().getComplement(), supplierDTO.complement, "Complements should match");
            assertEquals(expectedSupplier.getAddress().getNeighborhood(), supplierDTO.neighborhood, "Neighborhoods should match");
            assertEquals(expectedSupplier.getAddress().getCity(), supplierDTO.city, "Cities should match");
            assertEquals(expectedSupplier.getAddress().getState(), supplierDTO.state, "States should match");

            for(String expectedPhoneNumber : expectedSupplier.getPhoneNumbers()){
                assertTrue(supplierDTO.phoneNumbers.contains(expectedPhoneNumber), "All phone numbers should match");
            }
        });

        verify(supplierRepository).findById("124535fdvas2");
    }

    @Test
    public void createSupplierShouldThrowExceptionWhenAttemptToCreateSupplierWithTheSameDocument()
            throws DuplicateCreationAttemptException {
        when(supplierRepository.findByUserIdAndDocument("123abc", "47.960.950/0001-21"))
                .thenReturn(Optional.of(SupplierServiceTestData.getStandardSupplier()));

        SupplierCreationDTO supplierDTO = new SupplierCreationDTO();
        supplierDTO.document = "47.960.950/0001-21";

        assertThrows(DuplicateCreationAttemptException.class, () -> {
            supplierService.createSupplier(supplierDTO, "123abc");
        });

        verify(supplierRepository).findByUserIdAndDocument("123abc", "47.960.950/0001-21");
    }

    @Test
    public void createSupplierShouldReturnTheCreatedSupplier() throws DuplicateCreationAttemptException {
        when(supplierRepository.findByUserIdAndDocument("123abc", "47.960.950/0001-21"))
                .thenReturn(Optional.empty());
        when(supplierRepository.save(any(Supplier.class))).thenReturn(SupplierServiceTestData.getStandardSupplier());

        SupplierCreationDTO supplierDTO = SupplierServiceTestData.getStandardSupplierCreationDTO();
        Supplier returnedSupplier = supplierService.createSupplier(supplierDTO, "123abc");
        assertEquals(SupplierServiceTestData.getStandardSupplier(), returnedSupplier, "Expected supplier to match the created one");

        verify(supplierRepository).findByUserIdAndDocument("123abc", "47.960.950/0001-21");
    }

    @Test
    public void updateSupplierShouldThrowExceptionWhenSupplierDoesntExist(){
        when(supplierRepository.findById("123abc"))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            supplierService.updateSupplier("123abc", new SupplierUpdateDTO());
        });

        verify(supplierRepository).findById("123abc");
    }

    @Test
    public void updateSupplierShouldUpdateAndReturnSupplierWhenExists() throws ResourceNotFoundException {
        Supplier supplier = SupplierServiceTestData.getStandardSupplier();
        SupplierUpdateDTO supplierUpdateDTO = SupplierServiceTestData.getStandardSupplierUpdateDTO();

        when(supplierRepository.findById("124535fdvas2")).thenReturn(Optional.of(supplier));

        supplier.setMainContact(new SupplierMainContact("Marina", "marina@magazineluiza.com.br"));
        supplier.setPhoneNumbers(List.of("(11) 98144 - 9858"));

        when(supplierRepository.save(any(Supplier.class))).thenReturn(supplier);

        Optional<Supplier> updatedSupplierOpt = supplierService.updateSupplier("124535fdvas2", supplierUpdateDTO);

        assertTrue(updatedSupplierOpt.isPresent(), "Supplier should be returned");
        Supplier updatedSupplier = updatedSupplierOpt.get();


        assertEquals(supplierUpdateDTO.activitiesDescription, updatedSupplier.getActivitiesDescription(), "Activities description should match");
        assertEquals(supplierUpdateDTO.contactName, updatedSupplier.getMainContact().getName(), "Main contact's name should match");
        assertEquals(supplierUpdateDTO.contactEmail, updatedSupplier.getMainContact().getEmail(), "Main contact's email should match");
        assertEquals(supplierUpdateDTO.phoneNumbers.size(), updatedSupplier.getPhoneNumbers().size(), "Phone numbers' quantity should match");
        assertEquals(supplierUpdateDTO.zipCode, updatedSupplier.getAddress().getZipcode(), "Zipcode should match");
        assertEquals(supplierUpdateDTO.street, updatedSupplier.getAddress().getStreet(), "Street should match");
        assertEquals(supplierUpdateDTO.number, updatedSupplier.getAddress().getNumber(), "Number should match");
        assertEquals(supplierUpdateDTO.complement, updatedSupplier.getAddress().getComplement(), "Complement should match");
        assertEquals(supplierUpdateDTO.neighborhood, updatedSupplier.getAddress().getNeighborhood(), "Neighborhood should match");
        assertEquals(supplierUpdateDTO.city, updatedSupplier.getAddress().getCity(), "City should match");
        assertEquals(supplierUpdateDTO.state, updatedSupplier.getAddress().getState(), "State should match");

        for(String expectedPhoneNumber : supplier.getPhoneNumbers()){
            assertTrue(supplierUpdateDTO.phoneNumbers.contains(expectedPhoneNumber), "All phone numbers should match");
        }

        verify(supplierRepository).save(supplier);
    }

    @Test
    public void deleteSupplierShouldThrowExceptionWhenSupplierDoesntExist(){
        when(supplierRepository.findById("123abc"))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            supplierService.deleteSupplier("123abc");
        });
        verify(supplierRepository).findById("123abc");
    }

    @Test
    public void deleteSupplierShouldCallDeleteWhenSupplierExists() throws ResourceNotFoundException {
        Supplier supplier = SupplierServiceTestData.getStandardSupplier();
        when(supplierRepository.findById("124535fdvas2")).thenReturn(Optional.of(supplier));

        supplierService.deleteSupplier("124535fdvas2");

        verify(supplierRepository).delete(supplier);
    }

    private static Stream<SupplierForUserId> getSupplierListForUser(){

        List<SupplierForUserId> suppliers = new ArrayList<>();
        String userId = "u00";
        for(int i = 0; i < 8; i++){
            suppliers.add(new SupplierForUserId(userId + (i + 1),
                    SupplierServiceTestData.generateSuppliersForUserId(userId + (i + 1))));
        }

        return suppliers.stream();
    }
}
