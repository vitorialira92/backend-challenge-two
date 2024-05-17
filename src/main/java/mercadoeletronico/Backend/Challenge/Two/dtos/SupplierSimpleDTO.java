package mercadoeletronico.Backend.Challenge.Two.dtos;

import java.util.List;

public class SupplierSimpleDTO {
    public String id;
    public String name;
    public String document;

    public SupplierSimpleDTO(String id, String name, String document) {
        this.id = id;
        this.name = name;
        this.document = document;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }
}
