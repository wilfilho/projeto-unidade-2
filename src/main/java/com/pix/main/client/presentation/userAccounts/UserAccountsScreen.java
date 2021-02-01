package com.pix.main.client.presentation.userAccounts;

import com.pix.main.bank.domain.RetrieveAccountStatementsUseCase;
import com.pix.main.client.domain.AddAccountUseCase;
import com.pix.main.client.domain.RetrieveUserAccountsUseCase;
import com.pix.main.client.domain.errors.ClientNotFoundException;
import com.pix.main.client.domain.models.Account;
import com.pix.main.client.presentation.accountBankStatement.AccountScreen;
import com.pix.main.start.Main;
import com.pix.main.client.presentation.addAccount.AddAccountScreen;

import javax.security.auth.login.AccountNotFoundException;
import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserAccountsScreen extends JFrame {

    private RetrieveUserAccountsUseCase retrieveUserAccountsUseCase;

    private AddAccountUseCase addAccountUseCase;

    private RetrieveAccountStatementsUseCase retrieveAccountStatementsUseCase;

    private String clientId;

    public UserAccountsScreen(
            String clientId,
            RetrieveUserAccountsUseCase retrieveUserAccountsUseCase,
            AddAccountUseCase addAccountUseCase,
            RetrieveAccountStatementsUseCase retrieveAccountStatementsUseCase) throws IOException, ClientNotFoundException {
        this.retrieveAccountStatementsUseCase = retrieveAccountStatementsUseCase;
        this.retrieveUserAccountsUseCase = retrieveUserAccountsUseCase;
        this.clientId = clientId;
        this.addAccountUseCase = addAccountUseCase;
        configureScreen();
        createMainContent();
    }

    private void configureScreen() {
        setTitle("Contas do usuário");
        setSize(300, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void createMainContent() throws IOException, ClientNotFoundException {
        JMenuBar menuBar = new JMenuBar();

        JMenu bankMenu = new JMenu("Contas");
        JMenuItem addBankMenuItem = new JMenuItem("Adicionar nova conta");
        addBankMenuItem.addActionListener((event) -> new AddAccountScreen(addAccountUseCase, clientId));
        bankMenu.add(addBankMenuItem);

        JMenu logoutMenu = new JMenu("Sair");
        logoutMenu.addActionListener(e -> {
            dispose();
            Main.main(null);
        });

        menuBar.add(bankMenu);
        menuBar.add(logoutMenu);

        setJMenuBar(menuBar);

        JList accountsListView = new JList(getClientAccounts());
        accountsListView.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        accountsListView.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        accountsListView.setVisibleRowCount(-1);
        accountsListView.addListSelectionListener(e -> {
            JList source = (JList)e.getSource();
            String[] selected = source.getSelectedValue().toString().split("\\|");
            String accountId = selected[2].split(" ")[2];
            String bankId = selected[0].split(" ")[1];
            try {
                new AccountScreen(clientId, accountId, bankId, retrieveAccountStatementsUseCase);
            } catch (AccountNotFoundException accountNotFoundException) {
                accountNotFoundException.printStackTrace();
            } catch (ClientNotFoundException clientNotFoundException) {
                clientNotFoundException.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        JScrollPane listScroller = new JScrollPane(accountsListView);
        setContentPane(listScroller);
    }

    private Object[] getClientAccounts() throws IOException, ClientNotFoundException {
        List<Account> allAccounts = retrieveUserAccountsUseCase.getAllUserAccounts(clientId);
        ArrayList<String> accounts = new ArrayList<>();

        for (Account account : allAccounts) {
            String accountStr = String.format("Banco: %s | Agência: %s | Conta: %s", account.getBankId(), account.getAgencyId(), account.getAccountId());
            accounts.add(accountStr);
        }

        return accounts.toArray();
    }
}
