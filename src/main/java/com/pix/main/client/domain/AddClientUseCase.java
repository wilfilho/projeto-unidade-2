package com.pix.main.client.domain;

import com.pix.main.client.domain.errors.ClientAlreadyExistsException;
import com.pix.main.client.domain.errors.InvalidPersonCpfException;
import com.pix.main.client.domain.errors.InvalidPersonNameException;
import com.pix.main.client.domain.models.BankClient;
import com.pix.main.client.domain.repositories.BankClientRepository;
import com.pix.main.client.domain.validators.PersonCpfValidator;

import java.io.IOException;

public class AddClientUseCase {

    private BankClientRepository bankClientRepository;

    private PersonCpfValidator cpfValidator;

    public AddClientUseCase(BankClientRepository bankClientRepository, PersonCpfValidator personCpfValidator) {
        this.bankClientRepository = bankClientRepository;
        this.cpfValidator = personCpfValidator;
    }

    public void invoke(String clientCpf, String clientName) throws IOException, ClientAlreadyExistsException, InvalidPersonCpfException, InvalidPersonNameException {
        if (!cpfValidator.isValid(clientCpf))  {
            throw new InvalidPersonCpfException();
        }

        String[] names = clientName.split(" ");
        boolean hasValidName = true;

        if (names.length >= 2) {
            for (String name : names) {
                if (name.length() < 2) {
                    hasValidName = false;
                }
            }
        } else {
            hasValidName = false;
        }

        if (!hasValidName) {
            throw new InvalidPersonNameException();
        }


        BankClient bankClient = new BankClient();
        bankClient.setId(clientCpf);
        bankClient.setName(clientName);

        bankClientRepository.addClient(bankClient);
    }

}
