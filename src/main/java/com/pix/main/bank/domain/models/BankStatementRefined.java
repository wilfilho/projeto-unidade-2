package com.pix.main.bank.domain.models;

import java.math.BigDecimal;

public class BankStatementRefined {

    private BankStatementClientRefined requesterUser;

    private BankStatementClientRefined transactionUser;

    private BigDecimal transactionValue;

    private boolean isCredit;

    public BankStatementRefined(BankStatementClientRefined requesterUser, BankStatementClientRefined transactionUser, BigDecimal transactionValue, boolean isCredit) {
        this.requesterUser = requesterUser;
        this.transactionUser = transactionUser;
        this.transactionValue = transactionValue;
        this.isCredit = isCredit;
    }

    public BankStatementClientRefined getRequesterUser() {
        return requesterUser;
    }

    public void setRequesterUser(BankStatementClientRefined requesterUser) {
        this.requesterUser = requesterUser;
    }

    public BankStatementClientRefined getTransactionUser() {
        return transactionUser;
    }

    public void setTransactionUser(BankStatementClientRefined transactionUser) {
        this.transactionUser = transactionUser;
    }

    public BigDecimal getTransactionValue() {
        return transactionValue;
    }

    public void setTransactionValue(BigDecimal transactionValue) {
        this.transactionValue = transactionValue;
    }

    public boolean isCredit() {
        return isCredit;
    }

    public void setCredit(boolean credit) {
        isCredit = credit;
    }
}
