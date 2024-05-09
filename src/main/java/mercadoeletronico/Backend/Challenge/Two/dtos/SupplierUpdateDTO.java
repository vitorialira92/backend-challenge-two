package mercadoeletronico.Backend.Challenge.Two.dtos;

import jakarta.validation.constraints.NotNull;
import mercadoeletronico.Backend.Challenge.Two.domain.supplier.SupplierType;

import java.util.List;

public class SupplierUpdateDTO {
    @NotNull
    public String contactName;
    @NotNull
    public String contactEmail;
    @NotNull
    public List<String> phoneNumbers;
    @NotNull
    public String activitiesDescription;

}
