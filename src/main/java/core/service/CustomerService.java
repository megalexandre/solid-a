package core.service;

import core.model.Person;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class CustomerService {

    private static final String CPF_REGEX = "\\D";
    private static final int CNPJ_LENGTH = 14;
    private static final int CPF_SIZE = 11;
    public Person createCustomer(Person person){

        if(person.getDocumentNumber().length() == 11){

            if(isValidCPF(person.getDocumentNumber())){
                save(person);
            }

        } else if (person.getDocumentNumber().length() == 14){
            if(isValidCNPJ(person.getDocumentNumber())){
                save(person);
            }
        }

        return person;
    }

    private boolean isValidCPF(String documentNumber){

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

    private boolean isValidCNPJ(String cnpj) {
        if (cnpj.length() != CNPJ_LENGTH) {
            return false;
        }

        List<Integer> numbers = cnpj.chars()
                .filter(Character::isDigit)
                .map(Character::getNumericValue)
                .boxed()
                .collect(Collectors.toList());

        if (numbers.stream().allMatch(num -> num.equals(numbers.get(0)))) {
            return false;
        }

        int firstDigit = calculateFirstDigitVerifier(numbers);
        if (firstDigit != numbers.get(12)) {
            return false;
        }

        int secondDigit = calculateSecondDigitVerifier(numbers);

        return secondDigit == numbers.get(13);
    }

    private int calculateFirstDigitVerifier(List<Integer> numbers) {
        List<Integer> multipliers = List.of(5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2);
        int sum = IntStream.range(0, 12)
                .map(index -> numbers.get(index) * multipliers.get(index))
                .sum();
        int remainder = sum % 11;
        return remainder < 2 ? 0 : 11 - remainder;
    }

    private int calculateSecondDigitVerifier(List<Integer> numbers) {
        List<Integer> multipliers = List.of(6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2);
        int sum = IntStream.range(0, 13)
                .map(index -> numbers.get(index) * multipliers.get(index))
                .sum();
        int remainder = sum % 11;
        return remainder < 2 ? 0 : 11 - remainder;
    }

    private void save(Person person){
        throw new RuntimeException("Not implement yet");
    }
}
