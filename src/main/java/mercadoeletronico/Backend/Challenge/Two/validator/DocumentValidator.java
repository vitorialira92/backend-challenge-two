package mercadoeletronico.Backend.Challenge.Two.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DocumentValidator implements ConstraintValidator<ValidDocument, String> {
    @Override
    public void initialize(ValidDocument constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return validateCPF(value) || validateCNPJ(value);
    }

    private boolean validateCPF(String cpf) {
        cpf = cpf.replaceAll("\\D+", "");

        if(cpf.length() != 11) return false;

        if(isRepeatedDigits(cpf)) return false;

        int sum = 0, firstDigit, secondDigit;

        for(int i = 0; i < 9; i++)
            sum += (cpf.charAt(i) - '0') * (10 - i);

        firstDigit = 11 - (sum % 11);
        if(firstDigit == 10 || firstDigit == 11)
            firstDigit = 0;

        if(firstDigit != (cpf.charAt(9) - '0'))
            return false;

        sum = 0;
        for(int i = 0; i < 10; i++)
            sum += (cpf.charAt(i) - '0') * (11 - i);

        secondDigit = 11 - (sum % 11);

        if(secondDigit == 10 || secondDigit == 11)
            secondDigit = 0;

        return secondDigit == (cpf.charAt(10) - '0');
    }

    private boolean validateCNPJ(String cnpj) {
        cnpj = cnpj.replaceAll("\\D+", "");

        if(cnpj.length() != 14) return false;

        if(isRepeatedDigits(cnpj)) return false;

        int sum = 0, firstDigit, secondDigit;

        int[] multipliers = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        for(int i = 0; i < 12; i++)
            sum += (cnpj.charAt(i) - '0') * multipliers[i];

        firstDigit = sum % 11 < 2 ? 0 : 11 - (sum % 11);

        if(firstDigit != (cnpj.charAt(12) - '0'))
            return false;

        sum = 0;
        multipliers = new int[]{6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        for(int i = 0; i < 13; i++)
            sum += (cnpj.charAt(i) - '0') * multipliers[i];

        secondDigit = sum % 11 < 2 ? 0 : 11 - (sum % 11);

        return secondDigit == (cnpj.charAt(13) - '0');
    }
    public static boolean isRepeatedDigits(String input) {
        if (input == null || input.isEmpty()) return false;
        Pattern pattern = Pattern.compile("^(.)\\1*$");
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
}
