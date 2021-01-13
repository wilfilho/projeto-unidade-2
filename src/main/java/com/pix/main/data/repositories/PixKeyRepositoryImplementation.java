package com.pix.main.data.repositories;

import com.pix.main.data.retriever.PixStorageManager;
import com.pix.main.domain.models.PixKey;
import com.pix.main.domain.repositories.PixKeyRepository;

public class PixKeyRepositoryImplementation implements PixKeyRepository {

    private final PixStorageManager storageManager;

    public PixKeyRepositoryImplementation(PixStorageManager storageManager) {
        this.storageManager = storageManager;
    }

    @Override
    public void addPixKey(PixKey pixKey) {

    }

    @Override
    public void removePixKey(Integer pixKeyId) {

    }
}
