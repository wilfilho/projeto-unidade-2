package com.pix.main;

import com.google.gson.Gson;
import com.pix.main.data.repositories.AgencyRepositoryImplementation;
import com.pix.main.data.retriever.JsonPixStorageManager;
import com.pix.main.data.retriever.PixStorageManager;
import com.pix.main.domain.models.Agency;
import com.pix.main.domain.repositories.AgencyRepository;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        PixStorageManager pixStorageManager = new JsonPixStorageManager(new Gson());
        AgencyRepository agencyRepository = new AgencyRepositoryImplementation(pixStorageManager);

        Agency agency = new Agency();
        agency.setId("002");
        agency.setName("Agencia banese");

        agencyRepository.addAgency(agency, "047");
    }

}
