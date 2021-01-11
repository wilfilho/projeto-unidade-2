package com.pix.main.domain.repositories;

public interface StatementRepository {

     void fetchStatementsByAgency(Integer agencyId);

     void fetchStatementsByClient(Integer clientId);

}
