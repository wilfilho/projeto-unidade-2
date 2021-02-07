package com.pix.main.client.presentation.accountBankStatement;

import com.pix.main.bank.domain.AddAccountCashUseCase;
import com.pix.main.bank.domain.AddTransactionUseCase;
import com.pix.main.bank.domain.RetrieveAccountStatementsUseCase;
import com.pix.main.bank.domain.RetrieveBankCashByAccountUseCase;
import com.pix.main.bank.domain.models.BankStatementRefined;
import com.pix.main.bank.presentation.transfer.addExternalTransfer.AddExternalTransferScreen;
import com.pix.main.bank.presentation.transfer.addInternalTransfer.AddInternalTransfer;
import com.pix.main.client.domain.errors.ClientNotFoundException;
import com.pix.main.client.presentation.addUserCash.AddUserCashScreen;
import com.pix.main.client.presentation.userCash.UserCashScreen;

import javax.security.auth.login.AccountNotFoundException;
import javax.swing.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AccountScreen extends JFrame {

    private final String accountId;

    private final String clientId;

    private final String bankId;

    private final RetrieveAccountStatementsUseCase mRetrieveAccountStatementsUseCase;

    private final RetrieveBankCashByAccountUseCase mRetrieveBankCashByAccountUseCase;

    private final AddAccountCashUseCase mAddAccountCashUseCase;

    private final AddTransactionUseCase mAddTransactionUseCase;

    private JList transactionsListView;

    public AccountScreen(
            String clientId,
            String accountId,
            String bankId,
            RetrieveAccountStatementsUseCase retrieveAccountStatementsUseCase,
            RetrieveBankCashByAccountUseCase retrieveBankCashByAccountUseCase,
            AddAccountCashUseCase addAccountCashUseCase,
            AddTransactionUseCase addTransactionUseCase
    ) throws AccountNotFoundException, ClientNotFoundException, IOException {
        this.clientId = clientId;
        this.accountId = accountId;
        this.bankId = bankId;
        this.mRetrieveAccountStatementsUseCase = retrieveAccountStatementsUseCase;
        this.mRetrieveBankCashByAccountUseCase = retrieveBankCashByAccountUseCase;
        this.mAddAccountCashUseCase = addAccountCashUseCase;
        this.mAddTransactionUseCase = addTransactionUseCase;
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
        addCashMenuItem.addActionListener(e -> {
            new AddUserCashScreen(mAddAccountCashUseCase, accountId, clientId);
        });
        JMenuItem seeCashMenuItem = new JMenuItem("Ver saldo");
        seeCashMenuItem.addActionListener(e -> {
            try {
                new UserCashScreen(mRetrieveBankCashByAccountUseCase, accountId, clientId);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (AccountNotFoundException accountNotFoundException) {
                accountNotFoundException.printStackTrace();
            }
        });
        cashMenu.add(addCashMenuItem);
        cashMenu.add(seeCashMenuItem);

        JMenu transferMenu = new JMenu("Transferências");
        JMenuItem addTransferMenuItem = new JMenuItem("Fazer transferência interna");
        addTransferMenuItem.addActionListener(e -> {
            AddInternalTransfer internalTransfer = new AddInternalTransfer(mAddTransactionUseCase, accountId, bankId);
            internalTransfer.setOnTransferMade(() -> transactionsListView.setListData(getStatements()));
        });
        JMenuItem addTransferPixMenuItem = new JMenuItem("Fazer transferência com Pix");
        addTransferPixMenuItem.addActionListener(e -> {
            AddExternalTransferScreen addExternalTransferScreen =
                    new AddExternalTransferScreen(mAddTransactionUseCase, accountId, bankId);
            addExternalTransferScreen.setOnTransferMade(() -> transactionsListView.setListData(getStatements()));
        });
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

        transactionsListView = new JList(getStatements());
        transactionsListView.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        transactionsListView.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        transactionsListView.setVisibleRowCount(-1);

        JScrollPane listScroller = new JScrollPane(transactionsListView);
        setContentPane(listScroller);
    }

    private Object[] getStatements() throws IOException, ClientNotFoundException, AccountNotFoundException {
        List<BankStatementRefined> statements =
                mRetrieveAccountStatementsUseCase.retrieveAllAccountStatements(accountId, bankId);
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
