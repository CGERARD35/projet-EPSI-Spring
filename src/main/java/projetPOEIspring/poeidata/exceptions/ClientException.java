package projetPOEIspring.poeidata.exceptions;

public class ClientException extends RuntimeException{

    public ClientException() {
        super("Client not found");
    }

    public ClientException(String message) {
        super(message);
    }
}
