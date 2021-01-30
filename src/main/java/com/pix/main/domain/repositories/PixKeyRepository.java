package com.pix.main.domain.repositories;

import com.pix.main.domain.errors.PixKeyAlreadyExistsException;
import com.pix.main.domain.errors.PixKeyNotAddedException;
import com.pix.main.domain.models.PixKey;

import java.io.IOException;

public interface PixKeyRepository {

     void addPixKey(PixKey pixKey, String accountId, String clientId) throws IOException, PixKeyAlreadyExistsException, PixKeyNotAddedException;

}
