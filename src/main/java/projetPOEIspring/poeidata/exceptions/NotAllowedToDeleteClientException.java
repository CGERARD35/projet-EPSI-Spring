package projetPOEIspring.poeidata.exceptions;

public class NotAllowedToDeleteClientException extends RuntimeException {

    public NotAllowedToDeleteClientException(String message) {
        super (message);
    }

    public NotAllowedToDeleteClientException() {
        super ("Cannot delete the given client.");
    }
}
