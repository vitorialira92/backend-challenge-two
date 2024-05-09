package mercadoeletronico.Backend.Challenge.Two.exceptions;

public class ResourceNotFoundException extends Exception{
    public String resourceName;
    public ResourceNotFoundException(String resourceName){
        this.resourceName = resourceName;
    }
    public String getResourceName() {
        return resourceName;
    }
}
