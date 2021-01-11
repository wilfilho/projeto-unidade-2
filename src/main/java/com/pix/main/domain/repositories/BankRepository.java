package com.pix.main.domain.repositories;

import com.pix.main.domain.models.Bank;

public interface BankRepository {

     void addBank(Bank bank);

     void deleteBank(Integer bankId);

}
