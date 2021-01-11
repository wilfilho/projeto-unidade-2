package com.pix.main.domain.repositories;

import com.pix.main.domain.models.Account;
import com.pix.main.domain.models.Statement;

public interface AccountRepository {

     void addAccount(Account account);

     void removeAccount(Integer accountId);

     void addTransfer(Statement statement);
}
