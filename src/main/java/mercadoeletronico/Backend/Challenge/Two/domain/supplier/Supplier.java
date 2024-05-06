package mercadoeletronico.Backend.Challenge.Two.domain.supplier;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "suppliers")
public class Supplier {
    @Id
    private String id;
    private String name;
    private SupplierType type;
    private String document;
    private List<String> phoneNumbers;
    private String activitiesDescription;

}
