package application.request;

import core.model.Person;

public record CreateCustomerRequest(String name, String documentNumber) {
    public Person toPerson() {
        return new Person(name, documentNumber);
    }
}
