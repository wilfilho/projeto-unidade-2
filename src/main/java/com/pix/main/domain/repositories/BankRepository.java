package com.pix.main.domain.repositories;

import com.pix.main.domain.models.Bank;

import java.io.IOException;

public interface BankRepository {

     void addBank(Bank bank) throws IOException;

     void deleteBank(String bankId) throws IOException;

}
