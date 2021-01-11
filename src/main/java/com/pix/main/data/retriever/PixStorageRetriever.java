package com.pix.main.data.retriever;

import com.pix.main.domain.models.PixStorage;

import java.io.IOException;

public interface PixStorageRetriever {

    PixStorage retrievePixStorage() throws IOException;

    void savePixStorage(PixStorage newPixStorage);

}
