package core.model;

import core.PersonType;

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

    public boolean isValid(){
        if(type() == PersonType.LEGAL){
            return new CPF(documentNumber).isValid();
        } else if(type() == PersonType.INDIVIDUAL) {
            return new CNPJ(documentNumber).isValid();
        }

        return false;
    }


    public PersonType type(){
        if(documentNumber.length() == 11){
            return PersonType.INDIVIDUAL;
        } else if(documentNumber.length() == 14){
            return PersonType.LEGAL;
        }

        return PersonType.UNKNOWN;
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
