package com.pix.main.domain.repositories;

import com.pix.main.domain.models.Account;
import com.pix.main.domain.models.Statement;

import java.io.IOException;

public interface AccountRepository {

     void addAccount(Account account) throws IOException;

     void addTransfer(Statement statement);

}
