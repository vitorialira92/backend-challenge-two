package mercadoeletronico.Backend.Challenge.Two.services.Utils;

import mercadoeletronico.Backend.Challenge.Two.domain.supplier.Supplier;

import java.util.List;

public class SupplierForUserId {
    public String userId;
    public List<Supplier> suppliers;

    public SupplierForUserId(String userId, List<Supplier> suppliers) {
        this.userId = userId;
        this.suppliers = suppliers;
    }
}
