package com.pix.main.bank.domain;

import com.pix.main.bank.domain.errors.BankNotFoundException;
import com.pix.main.bank.domain.repositories.BankRepository;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class RetrieveCompanyCashUseCase {

    private BankRepository bankRepository;

    public RetrieveCompanyCashUseCase(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public String invoke(String bankId) throws IOException, BankNotFoundException {
        BigDecimal bankTotalCash = bankRepository.getBankTotalCash(bankId);
        DecimalFormat decimalFormat = new DecimalFormat("#,###,##0.00");

        return decimalFormat.format(bankTotalCash);
    }

}
