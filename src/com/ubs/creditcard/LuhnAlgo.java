package com.ubs.creditcard;

import java.util.Arrays;

/**
 * Created by Sam on 12/7/17.
 */
public class LuhnAlgo implements CreditCardVerification{

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

    private final int[] tranformToDigitArray(int[] tokenizedArray) {
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


    private final String removeWhitespace(String cardNum) {
        return cardNum.replaceAll(" ","");
    }

    private final int calculateLuhnScore(int score) {
        return score% LUHN_DIVISOR;
    }

    private final int generateDigitSum(int[] digitArr) {
        int sum=0;
        for(int i=0;i<digitArr.length;i++){
            sum += digitArr[i];
        }
        return sum;
    }

    private final int[] tokenizeToArray(String cardNumber) {
        int arr[] = new int[cardNumber.length()];
        for(int i=0;i<cardNumber.length();i++) {
            arr[i] = Integer.parseInt(cardNumber.substring(i,i+1));
        }
        return arr;
    }

    private final int[] tranformToLuhnSequence(int[] digitArray) {
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
