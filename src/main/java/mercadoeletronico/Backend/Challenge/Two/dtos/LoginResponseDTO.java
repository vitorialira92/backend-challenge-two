package mercadoeletronico.Backend.Challenge.Two.dtos;

public class LoginResponseDTO {
    public String token;
    public String userId;
    public String redirectUrl;

    public LoginResponseDTO(String token, String userId, String redirectUrl) {
        this.token = token;
        this.userId = userId;
        this.redirectUrl = redirectUrl;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
