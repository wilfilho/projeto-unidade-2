package com.pix.main.domain.repositories;

import com.pix.main.domain.errors.AccountAlreadyExistsException;
import com.pix.main.domain.errors.AccountBalanceNotUpdatedException;
import com.pix.main.domain.errors.AgencyNotFoundException;
import com.pix.main.domain.errors.ClientNotFoundException;
import com.pix.main.domain.models.Account;

import javax.security.auth.login.AccountNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;

public interface AccountRepository {

     void addAccount(Account account) throws IOException, AccountAlreadyExistsException, ClientNotFoundException, AgencyNotFoundException;

     void updateCash(BigDecimal valueToAdd, String accountId, String clientId) throws IOException, AccountBalanceNotUpdatedException;

     BigDecimal getTotalCash(String accountId, String clientId) throws IOException, AccountNotFoundException;

}
