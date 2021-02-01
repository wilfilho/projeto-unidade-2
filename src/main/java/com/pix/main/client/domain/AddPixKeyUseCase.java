package com.pix.main.client.domain;

import com.pix.main.client.domain.errors.InvalidEmailException;
import com.pix.main.client.domain.errors.InvalidPhoneNumberException;
import com.pix.main.client.domain.errors.PixKeyAlreadyExistsException;
import com.pix.main.client.domain.errors.PixKeyNotAddedException;
import com.pix.main.core.generators.RandomAlphaNumericGenerator;
import com.pix.main.client.domain.models.PixKey;
import com.pix.main.client.domain.repositories.PixKeyRepository;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddPixKeyUseCase {

    private final PixKeyRepository pixKeyRepository;

    private final RandomAlphaNumericGenerator generator;

    public AddPixKeyUseCase(PixKeyRepository pixKeyRepository, RandomAlphaNumericGenerator generator) {
        this.pixKeyRepository = pixKeyRepository;
        this.generator = generator;
    }

    public void addEmailPixKey(String emailPixKey, String accountId, String clientId) throws PixKeyAlreadyExistsException, IOException, PixKeyNotAddedException, InvalidEmailException {
        String regex = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(emailPixKey);
        if (!matcher.matches()) {
            throw new InvalidEmailException();
        }

        PixKey pixKey = new PixKey();
        pixKey.setKeyId(emailPixKey);

        pixKeyRepository.addPixKey(pixKey, accountId, clientId);
    }

    public void addPhonePixKey(String phonePixKey, String accountId, String clientId) throws PixKeyAlreadyExistsException, IOException, PixKeyNotAddedException, InvalidPhoneNumberException {
        String regex = "\\(?\\b([0-9]{2,3}|0((x|[0-9]){2,3}[0-9]{2}))\\)?\\s*[0-9]{4,5}[- ]*[0-9]{4}\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phonePixKey);
        if (!matcher.matches()) {
            throw new InvalidPhoneNumberException();
        }

        PixKey pixKey = new PixKey();
        pixKey.setKeyId(phonePixKey);

        pixKeyRepository.addPixKey(pixKey, accountId, clientId);
    }

    public void addRandomKey(String accountId, String clientId) throws PixKeyAlreadyExistsException, IOException, PixKeyNotAddedException {
        String randomKey = generator.generateString(20);

        PixKey pixKey = new PixKey();
        pixKey.setKeyId(randomKey);

        pixKeyRepository.addPixKey(pixKey, accountId, clientId);
    }

}
