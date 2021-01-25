package com.pix.main.domain;

import com.pix.main.domain.errors.BankAlreadyExistsException;
import com.pix.main.domain.models.Bank;
import com.pix.main.domain.repositories.BankRepository;

import java.io.IOException;

public class AddBankUseCase {

    private BankRepository bankRepository;

    public AddBankUseCase(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public void invoke(String bankId, String bankName) throws IOException, BankAlreadyExistsException {
        Bank bank = new Bank();
        bank.setId(bankId);
        bank.setName(bankName);

        bankRepository.addBank(bank);
    }

}
