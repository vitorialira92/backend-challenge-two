package mercadoeletronico.Backend.Challenge.Two.dtos;

import mercadoeletronico.Backend.Challenge.Two.domain.supplier.SupplierType;

import java.util.List;

public class SupplierCreationDTO {
    private String name;
    private SupplierType type;
    private String document;
    private String contactName;
    private String contactEmail;
    private List<String> phoneNumbers;
    private String activitiesDescription;
}
