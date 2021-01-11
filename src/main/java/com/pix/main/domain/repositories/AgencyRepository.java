package com.pix.main.domain.repositories;

import com.pix.main.domain.models.Agency;

public interface AgencyRepository {

     void addAgency(Agency agency);

     void removeAgency(Integer id);

}
