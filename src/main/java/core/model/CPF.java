package core.model;

public class CPF {

    private static final String CPF_REGEX = "\\D";
    private static final int CPF_SIZE = 11;
    private String number;

    public CPF(String number) {
        this.number = number;
    }

    public boolean isValid(){
        return true;
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



}
