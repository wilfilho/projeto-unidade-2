package com.pix.main.domain.repositories;

import com.pix.main.domain.errors.AgencyAlreadyExistsException;
import com.pix.main.domain.models.Agency;

import java.io.IOException;
import java.math.BigDecimal;

public interface AgencyRepository {

     void addAgency(Agency agency, String bankId) throws IOException, AgencyAlreadyExistsException;

     BigDecimal getAgencyTotalCash(String bankId, String agencyId) throws IOException;

}
