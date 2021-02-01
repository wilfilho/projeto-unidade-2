package com.pix.main.domain;

import com.pix.main.client.domain.repositories.AccountRepository;
import com.pix.main.bank.domain.repositories.AgencyRepository;

import javax.security.auth.login.AccountNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class RetrieveBankCashByAccountUseCase {

    private final AgencyRepository agencyRepository;

    private final AccountRepository accountRepository;

    public RetrieveBankCashByAccountUseCase(AgencyRepository agencyRepository, AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        this.agencyRepository = agencyRepository;
    }

    public String getAgencyTotalCash(String bankId, String agencyId) throws IOException {
        BigDecimal agencyTotalCash = agencyRepository.getAgencyTotalCash(bankId, agencyId);
        DecimalFormat decimalFormat = new DecimalFormat("#,###,##0.00");

        return decimalFormat.format(agencyTotalCash);
    }

    public String getAccountTotalCash(String accountId, String clientId) throws IOException, AccountNotFoundException {
        BigDecimal accountTotalCash = accountRepository.getTotalCash(accountId, clientId);
        DecimalFormat decimalFormat = new DecimalFormat("#,###,##0.00");

        return decimalFormat.format(accountTotalCash);
    }

}
