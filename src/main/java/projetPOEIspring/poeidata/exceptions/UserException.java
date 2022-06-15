package projetPOEIspring.poeidata.exceptions;

public class UserException extends RuntimeException{

    public UserException() {
        super("Cant find the user");
    }

    public UserException(String message) {
        super(message);
    }
}
