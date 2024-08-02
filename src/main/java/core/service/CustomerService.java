package core.service;

import core.model.Person;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private static final String CPF_REGEX = "\\D";
    private static final int CPF_SIZE = 11;
    public Person createCustomer(Person person){

        if(isValid(person.getDocumentNumber())){
            save(person);
        }

        return person;
    }

    private boolean isValid(String documentNumber){

        String clean = documentNumber.replaceAll(CPF_REGEX, "");

        if (clean.length() != CPF_SIZE) {
            return false;
        }

        if (clean.chars().allMatch(c -> c == clean.charAt(0))) {
            return false;
        }

        int digit1 = calculateDigit(clean.substring(0, 9));
        int digit2 = calculateDigit(clean.substring(0, 9) + digit1);

        return clean.endsWith(String.format("%d%d", digit1, digit2));
    }

    private int calculateDigit(String partialCpf) {
        int sum = 0;
        for (int i = 0; i < partialCpf.length(); i++) {
            int digit = Character.getNumericValue(partialCpf.charAt(i));
            sum += digit * (partialCpf.length() + 1 - i);
        }
        int remainder = sum % 11;
        return remainder < 2 ? 0 : 11 - remainder;
    }

    private void save(Person person){
        throw new RuntimeException("Not implement yet");
    }
}
