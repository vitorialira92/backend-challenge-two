package mercadoeletronico.Backend.Challenge.Two.domain.supplier;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "supplier_address")
public class Address {
    @Id
    private String id;
    private String zipcode;
    private String street;
    private String number;
    private String complement;
    private String neighborhood;
    private String city;
    private State state;
}
