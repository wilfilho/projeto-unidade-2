package com.pix.main.domain.repositories;

import com.pix.main.domain.models.BankClient;

public interface ClientRepository {

     void addClient(BankClient client);

     void removeClient(Integer clientId);

}
