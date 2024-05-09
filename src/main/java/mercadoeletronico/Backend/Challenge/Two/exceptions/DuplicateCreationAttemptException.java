package mercadoeletronico.Backend.Challenge.Two.exceptions;

public class DuplicateCreationAttemptException extends Exception{
    public String id;
    public String identifierName;
    public String resourceName;
    public DuplicateCreationAttemptException(String id, String identifierName, String resourceName){
        this.id = id;
        this.identifierName = identifierName;
        this.resourceName = resourceName;
    }

    public String getIdentifierName() {
        return identifierName;
    }

    public void setIdentifierName(String identifierName) {
        this.identifierName = identifierName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }
}
