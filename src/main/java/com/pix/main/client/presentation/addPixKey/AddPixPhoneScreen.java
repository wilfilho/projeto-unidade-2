package com.pix.main.client.presentation.addPixKey;

import com.pix.main.client.domain.AddPixKeyUseCase;
import com.pix.main.client.domain.errors.InvalidPhoneNumberException;
import com.pix.main.client.domain.errors.PixKeyAlreadyExistsException;
import com.pix.main.client.domain.errors.PixKeyNotAddedException;

import java.io.IOException;

public class AddPixPhoneScreen extends AddPixScreenBase {

    public AddPixPhoneScreen(
            String clientId,
            String accountId,
            AddPixKeyUseCase addPixKeyUseCase) {
        super(clientId, accountId, addPixKeyUseCase);
    }

    @Override
    protected void configureScreenTitle() {
        setTitle("Adicionar telefone chave pix");
    }

    @Override
    protected void addPixKey(String key) {
        try {
            mAddPixKeyUseCase.addPhonePixKey(key, mAccountId, mClientId);
            dispose();
        } catch (PixKeyAlreadyExistsException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PixKeyNotAddedException e) {
            e.printStackTrace();
        } catch (InvalidPhoneNumberException e) {
            e.printStackTrace();
        }
    }
}
