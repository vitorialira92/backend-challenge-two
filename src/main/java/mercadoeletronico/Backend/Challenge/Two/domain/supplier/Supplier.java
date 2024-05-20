package mercadoeletronico.Backend.Challenge.Two.domain.supplier;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "suppliers")
public class Supplier {
    @Id
    private String id;
    private final String userId;
    private final String name;
    private final SupplierType type;
    @Transient
    private final DocumentType documentType;
    private final String document;
    private SupplierMainContact mainContact;
    private Address address;
    private List<String> phoneNumbers;
    private String activitiesDescription;

    public Supplier(String userId, String name, SupplierType type,
                    String document, SupplierMainContact mainContact, Address address,
                    List<String> phoneNumbers, String activitiesDescription) {
        this.userId = userId;
        this.name = name;
        this.type = type;
        this.documentType = type == SupplierType.INDIVIDUAL ? DocumentType.CPF : DocumentType.CNPJ;
        this.document = document;
        this.mainContact = mainContact;
        this.address = address;
        this.phoneNumbers = phoneNumbers;
        this.activitiesDescription = activitiesDescription;
    }

    public String getUserId() {
        return userId;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public SupplierType getType() {
        return type;
    }

    public String getDocument() {
        return document;
    }

    public SupplierMainContact getMainContact() {
        return mainContact;
    }

    public void setMainContact(SupplierMainContact mainContact) {
        this.mainContact = mainContact;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public String getActivitiesDescription() {
        return activitiesDescription;
    }

    public void setActivitiesDescription(String activitiesDescription) {
        this.activitiesDescription = activitiesDescription;
    }
}
