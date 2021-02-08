package com.pix.main.bank.domain.repositories;

import com.pix.main.bank.domain.errors.AgencyAlreadyExistsException;
import com.pix.main.bank.domain.errors.BankNotFoundException;
import com.pix.main.bank.domain.models.Agency;

import java.io.IOException;
import java.math.BigDecimal;

public abstract class AgencyRepository {

     public abstract void addAgency(Agency agency, String bankId) throws IOException, AgencyAlreadyExistsException, BankNotFoundException;

     public abstract BigDecimal getAgencyTotalCash(String bankId, String agencyId) throws IOException;

}
