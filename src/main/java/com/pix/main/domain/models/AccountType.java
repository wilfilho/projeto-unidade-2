package com.pix.main.domain.models;

public enum AccountType {
    CORRENTE, POUPANCA, SALARIO;

    public static String toString(AccountType type) {
        if (type == CORRENTE) {
            return "CORRENTE";
        } else if (type == POUPANCA) {
            return "POUPANCA";
        }
        return "SALARIO";
    }

}
