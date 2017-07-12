import com.ubs.creditcard.CreditCardVerification;
import com.ubs.creditcard.LuhnAlgo;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Sam on 12/7/17.
 */
public class CreditCardVarificationTest {

    CreditCardVerification ccVerifier = new LuhnAlgo();

    @Test
    public void testCreditCardNumber(){
        Assert.assertEquals(true,ccVerifier.verifyCardNumber("4408 0412 3456 7893"));
    }


}
