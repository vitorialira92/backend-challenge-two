package mercadoeletronico.Backend.Challenge.Two.dtos;

import java.sql.Timestamp;

public class ErrorMessage {
    public Timestamp timestamp;
    public int statusCode;
    public String message;

    public ErrorMessage(int statusCode, String message) {
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.statusCode = statusCode;
        this.message = message;
    }
}
