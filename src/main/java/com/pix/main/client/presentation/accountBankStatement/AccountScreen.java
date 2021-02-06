package com.pix.main.client.presentation.accountBankStatement;

import com.pix.main.bank.domain.RetrieveAccountStatementsUseCase;
import com.pix.main.bank.domain.models.BankStatementRefined;
import com.pix.main.client.domain.errors.ClientNotFoundException;

import javax.security.auth.login.AccountNotFoundException;
import javax.swing.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AccountScreen extends JFrame {

    private String accountId;

    private String clientId;

    private String bankId;

    private RetrieveAccountStatementsUseCase retrieveAccountStatementsUseCase;

    public AccountScreen(
            String clientId,
            String accountId,
            String bankId,
            RetrieveAccountStatementsUseCase retrieveAccountStatementsUseCase) throws AccountNotFoundException, ClientNotFoundException, IOException {
        this.clientId = clientId;
        this.accountId = accountId;
        this.bankId = bankId;
        this.retrieveAccountStatementsUseCase = retrieveAccountStatementsUseCase;
        configureScreen();
        createMainContent();
    }

    private void configureScreen() {
        setTitle(String.format("Gerenciando conta: %s", accountId));
        setSize(600, 300);
        setLocationRelativeTo(null);
    }

    private void createMainContent() throws AccountNotFoundException, ClientNotFoundException, IOException {
        JMenuBar menuBar = new JMenuBar();

        JMenu cashMenu = new JMenu("Saldo");
        JMenuItem addCashMenuItem = new JMenuItem("Adicionar saldo");
        JMenuItem seeCashMenuItem = new JMenuItem("Ver saldo");
        cashMenu.add(addCashMenuItem);
        cashMenu.add(seeCashMenuItem);

        JMenu transferMenu = new JMenu("Transferências");
        JMenuItem addTransferMenuItem = new JMenuItem("Fazer transferência interna");
        JMenuItem addTransferPixMenuItem = new JMenuItem("Fazer transferência com Pix");
        transferMenu.add(addTransferMenuItem);
        transferMenu.add(addTransferPixMenuItem);

        JMenu goBackMenu = new JMenu("Sair");
        JMenuItem backScreenMenuItem = new JMenuItem("Voltar");
        backScreenMenuItem.addActionListener(e -> dispose());
        goBackMenu.add(backScreenMenuItem);

        menuBar.add(cashMenu);
        menuBar.add(transferMenu);
        menuBar.add(goBackMenu);

        setJMenuBar(menuBar);

        JList accountsListView = new JList(getStatements());
        accountsListView.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        accountsListView.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        accountsListView.setVisibleRowCount(-1);

        JScrollPane listScroller = new JScrollPane(accountsListView);
        setContentPane(listScroller);
    }

    private Object[] getStatements() throws IOException, ClientNotFoundException, AccountNotFoundException {
        List<BankStatementRefined> statements =
                retrieveAccountStatementsUseCase.retrieveAllAccountStatements(accountId, bankId);
        ArrayList<String> finalStatements = new ArrayList<>();

        for (BankStatementRefined statementRefined : statements) {
            String isCreditText = statementRefined.isCredit() ? "Recebeu" : "Transferiu";
            String accountPrefix = statementRefined.isCredit() ? "De: " : "Para: ";
            String accountNameText = String.format(
                    "%s %s",
                    accountPrefix,
                    statementRefined.getTransactionUser().getNameClient());
            String accountInfoText = String.format(
                    "Banco: %s | Conta %s",
                    statementRefined.getTransactionUser().getBankId(),
                    statementRefined.getTransactionUser().getAccountId());
            BigDecimal correctValue = statementRefined.getTransactionValue().multiply(new BigDecimal(-1));
            String valueTransfer = String.format("Valor: %s", correctValue);
            String finalItem = String.format(
                    "%s | %s | %s | %s", isCreditText, accountNameText, accountInfoText, valueTransfer);
            finalStatements.add(finalItem);
        }

        return finalStatements.toArray();
    }
}
