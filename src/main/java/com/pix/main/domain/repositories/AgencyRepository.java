package com.pix.main.domain.repositories;

import com.pix.main.domain.errors.AgencyAlreadyExistsException;
import com.pix.main.domain.models.Agency;

import java.io.IOException;

public interface AgencyRepository {

     void addAgency(Agency agency, String bankId) throws IOException, AgencyAlreadyExistsException;

}
