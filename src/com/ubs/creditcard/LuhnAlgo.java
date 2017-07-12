package com.ubs.creditcard;

import java.util.Arrays;

/**
 * Created by Sam on 12/7/17.
 */
public class LuhnAlgo implements CreditCardVerification{

    /*
    Credit card numbers can be validated with a process called the Luhn algorithm. Simply stated, the Luhn algorithm works like this:

    Starting with the next to last digit and continuing with every other digit going back to the beginning of the card, double the digit.
    Sum all doubled and untouched digits in the number.
    If that total is a multiple of 10, the number is valid.
    For example, given the card number 4408 0412 3456 7893:

    Orig  :  4 4 0 8 0 4 1 2 3 4   5 6   7 8   9 3
    Step 1:  8 4 0 8 0 4 2 2 6 4  10 6  14 8  18 3
    Step 2:  8+4+0+8+0+4+2+2+6+4+1+0+6+1+4+8+1+8+3 = 70
    Step 3:  70 % 10 == 0
     */

    private static final int LUHN_DIVISOR = 10;
    private static final int SIZE = 50;

    @Override
    public boolean verifyCardNumber(String cardNumber) {
        String modifiedCardNumber = removeWhitespace(cardNumber);
        int tokens[] = tokenizeToArray(modifiedCardNumber);
        int transformedLuhnSeq[] = tranformToLuhnSequence(tokens);
        int digits[] = tranformToDigitArray(transformedLuhnSeq);
        int cardDigitSum = generateDigitSum(digits);
        int luhnScore= calculateLuhnScore(cardDigitSum);
        if(luhnScore ==  0)
            return true;
        else
            return false;
    }

    public int[] tranformToDigitArray(int[] tokenizedArray) {
        int digits[]= new int[SIZE];
        int index=0;
        for(int i=0;i<tokenizedArray.length;i++) {
            if (tokenizedArray[i] > 9) {
                digits[index++] = tokenizedArray[i] / 10;
                digits[index++] = tokenizedArray[i] % 10;
            }
            else {
                digits[index++] = tokenizedArray[i];
            }
        }
        return Arrays.copyOfRange(digits,0,index);
    }


    public String removeWhitespace(String cardNum) {
        return cardNum.replaceAll(" ","");
    }

    public int calculateLuhnScore(int score) {
        return score% LUHN_DIVISOR;
    }

    public int generateDigitSum(int[] digitArr) {
        int sum=0;
        for(int i=0;i<digitArr.length;i++){
            sum += digitArr[i];
        }
        return sum;
    }

    public int[] tokenizeToArray(String cardNumber) {
        int arr[] = new int[cardNumber.length()];
        for(int i=0;i<cardNumber.length();i++) {
            arr[i] = Integer.parseInt(cardNumber.substring(i,i+1));
        }
        return arr;
    }

    public int[] tranformToLuhnSequence(int[] digitArray) {
        int transformedArr[] = new int[digitArray.length];
        boolean toggle=true;
        for(int i=digitArray.length-1;i>=0 ;i--) {
            if (toggle) {
                transformedArr[i]= digitArray[i];
                toggle = false;
            }
            else {
                transformedArr[i]= digitArray[i]*2;
                toggle = true;
            }
        }
        return transformedArr;
    }
}
