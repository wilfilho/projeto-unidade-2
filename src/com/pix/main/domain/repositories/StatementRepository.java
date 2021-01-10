package com.pix.main.domain.repositories;

public interface StatementRepository {

     @Serialized ("id")
     void fetchStatementsByAgency(Integer agencyId);
     @Serialized ("idAccountSender")
     void fetchStatementsByClient(Integer clientId);

}
