package mocks;

import com.pix.main.bank.domain.errors.BankAlreadyExistsException;
import com.pix.main.bank.domain.errors.BankNotFoundException;
import com.pix.main.bank.domain.models.Bank;
import com.pix.main.bank.domain.repositories.BankRepository;

import java.io.IOException;
import java.math.BigDecimal;

public class BankRepositoryTestImplementation implements BankRepository {

    @Override
    public void addBank(Bank bank) throws IOException, BankAlreadyExistsException {
    }

    @Override
    public BigDecimal getBankTotalCash(String bankId) throws IOException, BankNotFoundException {
        return null;
    }
}
