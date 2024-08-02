package core.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CNPJ {

    private static final int CNPJ_LENGTH = 14;
    private String number;
    public CNPJ(String number) {
        this.number = number;
    }

    public boolean isValid(){
        return true;
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


}
