package mercadoeletronico.Backend.Challenge.Two.services.Utils;

import mercadoeletronico.Backend.Challenge.Two.domain.supplier.*;
import mercadoeletronico.Backend.Challenge.Two.dtos.SupplierCreationDTO;
import mercadoeletronico.Backend.Challenge.Two.dtos.SupplierUpdateDTO;

import java.util.ArrayList;
import java.util.List;

public class SupplierServiceTestData {

    public static List<Supplier> generateSuppliersForUserId(String userId) {
        List<Supplier> suppliers = new ArrayList<>();

        SupplierMainContact contact1 = new SupplierMainContact("Maria Silva", "maria@example.com");
        contact1.setSupplierId(userId);

        suppliers.add(new Supplier(
                userId,
                "Maria's Catering",
                SupplierType.LEGAL_ENTITY,
                "12.345.678/0001-22",
                contact1,
                new Address("01000-000", "Rua São Bento", "1500", "Apto 303", "Centro", "São Paulo", State.SP),
                List.of("+55 11 2345-6789"),
                "Catering services and event planning."));

        SupplierMainContact contact2 = new SupplierMainContact("João Costa", "joao.tech@example.com");
        contact2.setSupplierId(userId);

        suppliers.add(new Supplier(
                userId,
                "Tech Solutions Ltd.",
                SupplierType.LEGAL_ENTITY,
                "98.765.432/0001-11",
                contact2,
                new Address("20031-007", "Avenida Presidente Vargas", "500", "10º andar", "Cidade Nova", "Rio de Janeiro", State.RJ),
                List.of("+55 21 3456-7890"),
                "Software development and IT consulting."));

        SupplierMainContact contact3 = new SupplierMainContact("Ana Ferreira", "ana@greenhouse.com");
        contact3.setSupplierId(userId);

        suppliers.add(new Supplier(
                userId,
                "Greenhouse Organics",
                SupplierType.LEGAL_ENTITY,
                "56.789.010/0001-55",
                contact3,
                new Address("30110-044", "Rua dos Tupis", "750", "Sala 204", "Barro Preto", "Belo Horizonte", State.MG),
                List.of("+55 31 1234-5678"),
                "Organic food products distribution."));

        SupplierMainContact contact4 = new SupplierMainContact("Carlos Mendes", "carlos@quickrepair.com");
        contact4.setSupplierId(userId);

        suppliers.add(new Supplier(
                userId,
                "Quick Repair Auto",
                SupplierType.LEGAL_ENTITY,
                "47.258.369/0001-15",
                contact4,
                new Address("90200-200", "Rua Voluntários da Pátria", "194", "Loja 5", "Santana", "Porto Alegre", State.RS),
                List.of("+55 51 3322-4455"),
                "Automotive repair services."));

        SupplierMainContact contact5 = new SupplierMainContact("Teresa Campos", "teresa@booksandcoffee.com");
        contact5.setSupplierId(userId);

        suppliers.add(new Supplier(
                userId,
                "Books & Coffee",
                SupplierType.INDIVIDUAL,
                "294.683.538-22",
                contact5,
                new Address("70040-010", "SCS Quadra 2", "312", "Bloco C", "Asa Sul", "Brasília", State.DF),
                List.of("+55 61 3345-6677"),
                "Local bookstore and café."));

        return suppliers;
    }

    public static SupplierUpdateDTO getStandardSupplierUpdateDTO(){
        SupplierUpdateDTO supplierDTO = new SupplierUpdateDTO();
        supplierDTO.contactName = "Marina";
        supplierDTO.contactEmail = "marina@magazineluiza.com.br";
        supplierDTO.phoneNumbers = List.of("(11) 98144 - 9858");
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

    public static SupplierCreationDTO getStandardSupplierCreationDTO(){
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

    public static Supplier getStandardSupplier(){
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
}
