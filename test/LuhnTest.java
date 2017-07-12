import com.ubs.creditcard.LuhnAlgo;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Sam on 12/7/17.
 */
public class LuhnTest  {

    LuhnAlgo luhnAlgo = new LuhnAlgo();

    @Test
    public void testCreditCardNumber(){
        Assert.assertEquals(true,luhnAlgo.verifyCardNumber("4408 0412 3456 7893"));
        Assert.assertEquals(false,luhnAlgo.verifyCardNumber("4438 0412 3456 7893"));
    }

    @Test
    public void testTokenize(){
        String cardNumber = "4408041234567893"; // Input from the remove whitespace method
        int arr[] = {4,4,0,8,0,4,1,2,3,4,5,6,7,8,9,3};
        Assert.assertArrayEquals(arr,luhnAlgo.tokenizeToArray(cardNumber));
    }
    @Test
    public void testGenerateDigitSum(){
        int arr[] = {8,4,0,8,0,4,2,2,6,4,1,0,6,1,4,8,1,8,3};
        Assert.assertEquals(70, luhnAlgo.generateDigitSum(arr));
    }
    @Test
    public void testTranformToLuhnSequence(){
        int arr[] = {4,4,0,8,0,4,1,2,3,4,5,6,7,8,9,3};
        int luhnArray[] = {8,4,0,8,0,4,2,2,6,4,10,6,14,8,18,3};
        Assert.assertArrayEquals(luhnArray,luhnAlgo.tranformToLuhnSequence(arr));
    }
    @Test
    public void testTranformToDigitArray(){
        int arr[] = {8,4,0,8,0,4,2,2,6,4,10,6,14,8,18,3};
        int transformedArray[] = {8,4,0,8,0,4,2,2,6,4,1,0,6,1,4,8,1,8,3};
        Assert.assertArrayEquals(transformedArray,luhnAlgo.tranformToDigitArray(arr));
    }
    @Test
    public void testRemoveWhiteSpaces(){
        Assert.assertEquals("asddaaffdd",luhnAlgo.removeWhitespace("asdd aa ff   dd"));
    }
    @Test
    public void testCalculateScore(){
        Assert.assertEquals(0,luhnAlgo.calculateLuhnScore(70));
        Assert.assertEquals(5,luhnAlgo.calculateLuhnScore(75));
    }

}
