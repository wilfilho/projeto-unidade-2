package com.pix.main.domain.repositories;

import com.pix.main.domain.models.Statement;

import java.io.IOException;
import java.util.ArrayList;

public interface StatementRepository {

     ArrayList<Statement> fetchStatementsByAgency(String agencyId, String bankId) throws IOException;

     ArrayList<Statement> fetchStatementsByClient(String clientId);

}
