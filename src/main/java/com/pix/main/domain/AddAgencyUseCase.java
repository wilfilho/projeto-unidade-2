package com.pix.main.domain;

import com.pix.main.domain.errors.AgencyAlreadyExistsException;
import com.pix.main.domain.errors.BankNotFoundException;
import com.pix.main.domain.models.Agency;
import com.pix.main.domain.repositories.AgencyRepository;

import java.io.IOException;

public class AddAgencyUseCase {

    private AgencyRepository agencyRepository;

    public AddAgencyUseCase(AgencyRepository agencyRepository) {
        this.agencyRepository = agencyRepository;
    }

    public void invoke(String bankId, String agencyId, String agencyName) throws IOException, AgencyAlreadyExistsException, BankNotFoundException {
        Agency agency = new Agency();
        agency.setId(agencyId);
        agency.setName(agencyName);

        agencyRepository.addAgency(agency, bankId);
    }

}
