package com.pix.main.client.domain.repositories;

import com.pix.main.client.domain.errors.PixKeyAlreadyExistsException;
import com.pix.main.client.domain.errors.PixKeyNotAddedException;
import com.pix.main.client.domain.models.PixKey;

import java.io.IOException;

public interface PixKeyRepository {

     void addPixKey(PixKey pixKey, String accountId, String clientId) throws IOException, PixKeyAlreadyExistsException, PixKeyNotAddedException;

}
