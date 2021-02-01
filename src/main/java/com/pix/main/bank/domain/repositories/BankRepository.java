package com.pix.main.domain.repositories;

import com.pix.main.bank.domain.errors.BankAlreadyExistsException;
import com.pix.main.bank.domain.errors.BankNotFoundException;
import com.pix.main.domain.models.Bank;

import java.io.IOException;
import java.math.BigDecimal;

public interface BankRepository {

     void addBank(Bank bank) throws IOException, BankAlreadyExistsException;

     BigDecimal getBankTotalCash(String bankId) throws IOException, BankNotFoundException;

}
