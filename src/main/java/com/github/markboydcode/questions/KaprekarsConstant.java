package com.github.markboydcode.questions;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Calculates for any four digit number the number of iterations it takes to arrive at Kaprekar's constant of 6174 by
 * the following steps:
 *
 * 1) Take any four digit number (whose digits are not all identical).
 * 2) Rearrange the string of digits to form the largest and smallest 4-digit numbers possible.
 * 3) Take these two numbers and subtract the smaller number from the larger.
 * 4) Use the number you obtain and repeat the above process until you arrive at Kaprekar's constant of 6174.
 * 5) Return the count of the number of iterations it took to arrive at the constant.
 *
 * @author boydmr
 */
public class KaprekarsConstant {
  static final String TOO_LOW = "the value is less than or equal to zero";
  static final String TOO_HIGH = "the value is greater than 9999";
  static final String NO_VARIANCE = "the value contains digits that are all identical";

  private static final int THE_CONSTANT = 6174;

  private static final String FOUR_DIGIT_FORMAT =  "%04d";
  private static final int DIGITS =  4;

  /**
   *
   * @param fourDigitNumber the number to be tested
   * @return the number of iterations to arrive at Kaprekar's constant.
   * @throws IllegalArgumentException with one of three messages: "the value is greater than 9999",
   *  "the value is less than or equal to zero", or "the value contains digits that are all identical".
   */
  public int calculateIterations(int fourDigitNumber) {
    if (fourDigitNumber <= 0) {
      throw new IllegalArgumentException(TOO_LOW);
    } else if (fourDigitNumber > 9999) {
      throw new IllegalArgumentException(TOO_HIGH);
    }

    int count = 0;
    while(true){
      count++;
      String s =  String.format(FOUR_DIGIT_FORMAT, fourDigitNumber);
      Integer[] values  = new Integer[4];
      for(int j = 0; j < s.length(); j++){
        String c = s.substring(j,j+1);
        values[j] = Integer.valueOf(c);
      }
      Arrays.sort(values);
      String sLowest = Arrays.stream(values)
          .map(i -> String.valueOf(i))
          .collect(Collectors.joining());
      int low = padLeadingZeros(sLowest);
      int high = padLeadingZeros(new StringBuilder(sLowest).reverse().toString());
      fourDigitNumber = high - low;
      if(fourDigitNumber == THE_CONSTANT)
        return count;
    }
  }

  private int padLeadingZeros(String value) {
    String result = value;
    if(value != null && value.length() < DIGITS){
      result = String.format(FOUR_DIGIT_FORMAT, Integer.valueOf(value));
    }
    return Integer.valueOf(result);
  }
}
