package com.pix.main.models.repositories;

import com.pix.main.models.Agency;

public interface AgencyRepository {

     void addAgency(Agency agency);

     void removeAgency(Integer id);

}
