package core.model;

public class Person {

    private String name;
    private String documentNumber;
    public Person(String name, String documentNumber) {
        this.name = name;
        this.documentNumber = documentNumber;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }
}
