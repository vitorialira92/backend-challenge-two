package mercadoeletronico.Backend.Challenge.Two.domain.supplier;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "suppliers_contact")
public class SupplierMainContact {
    @Id
    private String id;
    private String supplierId;
    private String name;
    private String email;

    public SupplierMainContact(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public String getId() {
        return id;
    }

    public String setId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
