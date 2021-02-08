package com.pix.main.bank.domain.repositories;

import com.pix.main.bank.domain.errors.BankAlreadyExistsException;
import com.pix.main.bank.domain.errors.BankNotFoundException;
import com.pix.main.bank.domain.models.Bank;

import java.io.IOException;
import java.math.BigDecimal;

public abstract class BankRepository {

     public abstract void addBank(Bank bank) throws IOException, BankAlreadyExistsException;

     public abstract BigDecimal getBankTotalCash(String bankId) throws IOException, BankNotFoundException;

}
