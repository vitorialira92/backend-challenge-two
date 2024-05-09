package mercadoeletronico.Backend.Challenge.Two.dtos;

import jakarta.validation.constraints.NotNull;

public class LoginDTO {
    @NotNull
    public String email;
    @NotNull
    public String password;

    public @NotNull String getPassword() {
        return password;
    }

    public void setPassword(@NotNull String password) {
        this.password = password;
    }

    public @NotNull String getEmail() {
        return email;
    }

    public void setEmail(@NotNull String email) {
        this.email = email;
    }
}
