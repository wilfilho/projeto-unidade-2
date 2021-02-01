package com.pix.main.core.storage;

import com.pix.main.client.domain.models.PixStorage;

import java.io.IOException;

public interface PixStorageManager {

    PixStorage retrievePixStorage() throws IOException;

    void savePixStorage(PixStorage newPixStorage) throws IOException;

}
