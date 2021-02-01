package com.pix.main.domain.validators;

public class PersonCpfValidator {

    public boolean isValid(String cpfToValidate) {
        if (cpfToValidate.length() != 11)
            return false;

        return cpfToValidate.chars().allMatch(Character::isDigit);
    }

}
