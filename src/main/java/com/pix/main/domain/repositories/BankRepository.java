package com.pix.main.domain.repositories;

import com.pix.main.domain.errors.BankAlreadyExistsException;
import com.pix.main.domain.models.Bank;

import java.io.IOException;

public interface BankRepository {

     void addBank(Bank bank) throws IOException, BankAlreadyExistsException;

}
