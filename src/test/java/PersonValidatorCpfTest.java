import com.pix.main.client.domain.validators.PersonCpfValidator;
import org.junit.Assert;
import org.junit.Test;


public class PersonValidatorCpfTest {

    @Test
    public void testWithInvalidCPF() {
        PersonCpfValidator personCpfValidator = new PersonCpfValidator();

        Assert.assertFalse(personCpfValidator.isValid("jdnajsda"));
        Assert.assertFalse(personCpfValidator.isValid("00000000000"));
        Assert.assertFalse(personCpfValidator.isValid("33333333333"));
        Assert.assertFalse(personCpfValidator.isValid("22222222222"));
        Assert.assertFalse(personCpfValidator.isValid("77777777777"));
        Assert.assertFalse(personCpfValidator.isValid("0614553322"));
    }

    @Test
    public void testWithValidCPFs() {
        PersonCpfValidator personCpfValidator = new PersonCpfValidator();

        Assert.assertTrue(personCpfValidator.isValid("23820389008"));
        Assert.assertTrue(personCpfValidator.isValid("79031901059"));
        Assert.assertTrue(personCpfValidator.isValid("58811725070"));
        Assert.assertTrue(personCpfValidator.isValid("29254743062"));
    }

}
