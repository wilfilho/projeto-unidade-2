package com.pix.main.domain.repositories;

import com.pix.main.domain.models.BankClient;

import java.io.IOException;

public interface ClientRepository {

     void addClient(BankClient client) throws IOException;

}
