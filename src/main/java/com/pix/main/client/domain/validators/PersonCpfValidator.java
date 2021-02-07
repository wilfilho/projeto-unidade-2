package com.pix.main.client.domain.validators;

import java.util.InputMismatchException;

/**
 * Code from: https://github.com/feharaujo/Cpf-Validator/blob/master/src/com/fearaujo/CpfValidator.java
 */
public class PersonCpfValidator {

    public boolean isValid(String cpfToValidate) {
        if (cpfToValidate == null)
            return false;

        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (cpfToValidate.equals("00000000000") || cpfToValidate.equals("11111111111") || cpfToValidate.equals("22222222222") ||
                cpfToValidate.equals("33333333333") || cpfToValidate.equals("44444444444") || cpfToValidate.equals("55555555555")
                || cpfToValidate.equals("66666666666") || cpfToValidate.equals("77777777777") || cpfToValidate.equals("88888888888")
                || cpfToValidate.equals("99999999999") || (cpfToValidate.length() != 11))
            return(false);
        char dig10,
                dig11;
        int sm, i, r, num, peso;
        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0; peso = 10; for (i=0; i<9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posicao de '0' na tabela ASCII)
                num = (int)(cpfToValidate.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }
            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48);
            // converte no respectivo caractere numerico
            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
                num = (int)(cpfToValidate.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }
            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char)(r + 48);
            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == cpfToValidate.charAt(9)) && (dig11 == cpfToValidate.charAt(10)))
                return(true);
            else
                return(false);
        } catch (InputMismatchException erro) {
            return(false);
        }
    }

}
