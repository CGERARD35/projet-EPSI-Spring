package projetPOEIspring.poeidata.exceptions;

public class OrderException extends RuntimeException{

    public OrderException() {
        super("Cant find the order");
    }

    public OrderException(String message) {
        super(message);
    }
}
