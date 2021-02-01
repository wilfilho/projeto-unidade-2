package com.pix.main.data.storage;

import com.pix.main.domain.models.PixStorage;

import java.io.IOException;

public interface PixStorageManager {

    PixStorage retrievePixStorage() throws IOException;

    void savePixStorage(PixStorage newPixStorage) throws IOException;

}
