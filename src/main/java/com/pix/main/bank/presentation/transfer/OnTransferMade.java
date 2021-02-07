package com.pix.main.bank.presentation.transfer;

import com.pix.main.client.domain.errors.ClientNotFoundException;

import javax.security.auth.login.AccountNotFoundException;
import java.io.IOException;

public interface OnTransferMade {

    void onNewTransfer() throws AccountNotFoundException, ClientNotFoundException, IOException;

}
