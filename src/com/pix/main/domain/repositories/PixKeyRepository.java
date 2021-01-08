package com.pix.main.domain.repositories;

import com.pix.main.domain.models.PixKey;

public interface PixKeyRepository {

     void addPixKey(PixKey pixKey);

     void removePixKey(Integer pixKeyId);

}
