package projetPOEIspring.poeidata.Enums;

public enum PayementOrder {
    IMPAYEE("Impayée"),
    PAYEE("Payée");

    private final String name;

    private PayementOrder(String s) {
        name = s;
    }

    public String toString() {
        return this.name;
    }
}
