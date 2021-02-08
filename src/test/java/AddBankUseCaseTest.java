import com.pix.main.bank.domain.AddBankUseCase;
import com.pix.main.bank.domain.errors.BankAlreadyExistsException;
import com.pix.main.bank.domain.errors.InvalidBankIdException;
import com.pix.main.bank.domain.repositories.BankRepository;
import mocks.BankRepositoryTestImplementation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class AddBankUseCaseTest {

    private BankRepository bankRepository;

    private AddBankUseCase addBankUseCase;

    @Before
    public void setUp() {
        bankRepository = new BankRepositoryTestImplementation();
        addBankUseCase = new AddBankUseCase(bankRepository);
    }

    @Test
    public void testWithInvalidBankId() {
        Assert.assertThrows(InvalidBankIdException.class, () -> {
            addBankUseCase.invoke("12344", "Banco Itaú");
        });
    }

    @Test
    public void testWithValidBankIdName() throws InvalidBankIdException, IOException, BankAlreadyExistsException {
        addBankUseCase.invoke("341", "Banco Itaú");
    }

}
