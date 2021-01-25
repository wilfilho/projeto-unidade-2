package com.pix.main.domain.repositories;

import com.pix.main.domain.errors.ClientAlreadyExistsException;
import com.pix.main.domain.models.BankClient;

import java.io.IOException;

public interface BankClientRepository {

     void addClient(BankClient client) throws IOException, ClientAlreadyExistsException;

}
