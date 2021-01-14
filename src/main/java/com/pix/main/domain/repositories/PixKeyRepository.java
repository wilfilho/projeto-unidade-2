package com.pix.main.domain.repositories;

import com.pix.main.domain.models.PixKey;

import java.io.IOException;

public interface PixKeyRepository {

     void addPixKey(PixKey pixKey, String accountId) throws IOException;

}
