package com.pix.main.domain.repositories;

import com.pix.main.domain.errors.AccountAlreadyExistsException;
import com.pix.main.domain.errors.AgencyNotFoundException;
import com.pix.main.domain.errors.ClientNotFoundException;
import com.pix.main.domain.models.Account;
import com.pix.main.domain.models.Statement;

import java.io.IOException;

public interface AccountRepository {

     void addAccount(Account account) throws IOException, AccountAlreadyExistsException, ClientNotFoundException, AgencyNotFoundException;

     void addTransfer(Statement statement);

}
