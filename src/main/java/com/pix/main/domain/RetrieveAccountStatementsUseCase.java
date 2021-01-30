package com.pix.main.domain;

import com.pix.main.domain.errors.ClientNotFoundException;
import com.pix.main.domain.models.BankClient;
import com.pix.main.domain.models.BankStatement;
import com.pix.main.domain.models.BankStatementClientRefined;
import com.pix.main.domain.models.BankStatementRefined;
import com.pix.main.domain.repositories.BankClientRepository;
import com.pix.main.domain.repositories.BankStatementRepository;

import javax.security.auth.login.AccountNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class RetrieveAccountStatementsUseCase {

    private final BankStatementRepository bankStatementRepository;

    private final BankClientRepository bankClientRepository;

    public RetrieveAccountStatementsUseCase(BankStatementRepository bankStatementRepository, BankClientRepository bankClientRepository) {
        this.bankStatementRepository = bankStatementRepository;
        this.bankClientRepository = bankClientRepository;
    }

    public List<BankStatementRefined> retrieveAllAccountStatements(String accountId, String bankId) throws IOException, AccountNotFoundException, ClientNotFoundException {
        List<BankStatement> allAccountStatements = bankStatementRepository.getBankStatementByAccount(accountId, bankId);
        ArrayList<BankStatementRefined> allRefinedAccountStatements = new ArrayList<>();

        BankClient requesterClient = bankClientRepository.getClientByAccount(accountId, bankId);

        for (BankStatement statement : allAccountStatements) {
            BankClient clientTransaction = bankClientRepository.getClientByAccount(statement.getAccountTransactionId(), statement.getBankTransactionId());

            BankStatementClientRefined requester = new BankStatementClientRefined(accountId, bankId, requesterClient.getName());
            BankStatementClientRefined userTransaction = new BankStatementClientRefined(statement.getAccountTransactionId(), statement.getBankTransactionId(), clientTransaction.getName());
            boolean isCredit = statement.getValue().compareTo(BigDecimal.ZERO) >= 0;

            allRefinedAccountStatements.add(new BankStatementRefined(requester, userTransaction, statement.getValue(), isCredit));
        }

        return allRefinedAccountStatements;
    }

}
