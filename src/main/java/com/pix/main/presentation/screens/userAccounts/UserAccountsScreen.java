package com.pix.main.presentation.screens.userAccounts;

import com.pix.main.domain.AddAccountUseCase;
import com.pix.main.domain.RetrieveUserAccountsUseCase;
import com.pix.main.domain.errors.ClientNotFoundException;
import com.pix.main.domain.models.Account;
import com.pix.main.presentation.screens.addAccount.AddAccountScreen;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserAccountsScreen extends JFrame implements FocusListener {

    private RetrieveUserAccountsUseCase retrieveUserAccountsUseCase;

    private AddAccountUseCase addAccountUseCase;

    private String clientId;

    public UserAccountsScreen(
            String clientId,
            RetrieveUserAccountsUseCase retrieveUserAccountsUseCase,
            AddAccountUseCase addAccountUseCase) throws IOException, ClientNotFoundException {
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
        addFocusListener(this);
    }

    private void createMainContent() throws IOException, ClientNotFoundException {
        JMenuBar menuBar = new JMenuBar();

        JMenu bankMenu = new JMenu("Contas");
        JMenuItem addBankMenuItem = new JMenuItem("Adicionar nova conta");
        addBankMenuItem.addActionListener((event) -> new AddAccountScreen(addAccountUseCase, clientId));
        bankMenu.add(addBankMenuItem);

        menuBar.add(bankMenu);

        setJMenuBar(menuBar);

        JList accountsListView = new JList(getClientAccounts());
        accountsListView.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        accountsListView.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        accountsListView.setVisibleRowCount(-1);

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

    @Override
    public void focusGained(FocusEvent e) {
        try {
            JList accountsListView = new JList(getClientAccounts());
            accountsListView.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            accountsListView.setLayoutOrientation(JList.HORIZONTAL_WRAP);
            accountsListView.setVisibleRowCount(-1);

            JScrollPane listScroller = new JScrollPane(accountsListView);
            setContentPane(listScroller);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (ClientNotFoundException clientNotFoundException) {
            clientNotFoundException.printStackTrace();
        }
    }

    @Override
    public void focusLost(FocusEvent e) {}
}
