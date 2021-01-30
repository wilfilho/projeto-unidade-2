package com.pix.main.domain.repositories;

import com.pix.main.domain.models.BankStatement;

import javax.security.auth.login.AccountNotFoundException;
import java.io.IOException;
import java.util.List;

public interface BankStatementRepository {

    void addTransfer(BankStatement dataTransaction, String accountId, String bankId) throws IOException, AccountNotFoundException;

    List<BankStatement> getBankStatementByAccount(String accountId, String bankId) throws IOException, AccountNotFoundException;

}
