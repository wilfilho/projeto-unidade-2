package com.pix.main.domain;

import com.pix.main.domain.errors.BankAlreadyExistsException;
import com.pix.main.domain.errors.InvalidBankIdException;
import com.pix.main.domain.models.Bank;
import com.pix.main.domain.repositories.BankRepository;

import java.io.IOException;

public class AddBankUseCase {

    private final BankRepository bankRepository;

    public AddBankUseCase(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public void invoke(String bankId, String bankName) throws IOException, BankAlreadyExistsException, InvalidBankIdException {
        if (bankId.length() < 3) {
            throw new InvalidBankIdException();
        }

        Bank bank = new Bank();
        bank.setId(bankId);
        bank.setName(bankName);

        bankRepository.addBank(bank);
    }

}
