/**
 * @author Mr. Rasmussen
 */

package fracCalc;

import java.util.*;

public class FracCalc {
	// ** IMPORTANT ** DO NOT DELETE THIS FUNCTION. This function will be used to
	// test your code
	// This function takes a String 'input' and produces the result
	//
	// input is a fraction string that needs to be evaluated. For your program, this
	// will be the user input.
	// e.g. input ==> "1/2 + 3/4"
	//
	// The function should return the result of the fraction after it has been
	// calculated
	// e.g. return ==> "1_1/4"

	public static void main(String[] args) {
		Scanner userInput = new Scanner(System.in);
		String equation = userInput.nextLine();
		while (!equation.equals("quit")) {
			System.out.println(produceAnswer(equation));
			equation = userInput.nextLine();
		}
		userInput.close();
	}

	public static String produceAnswer(String input) {
		int spaceIndex = input.indexOf(' ');
		String firstOperand = input.substring(0, spaceIndex);
		String operator = input.substring(input.indexOf(" ") + 1, spaceIndex + 2);
		String secondOperand = input.substring(spaceIndex + 3);
		return operandSeg(secondOperand);
	}
		/*
		String one = operandSeg(firstOperand);
		int oneNum = intConvert(one.substring(0, one.indexOf('/')));
		int oneDen = intConvert(one.substring(one.indexOf('/') + 1));
		String two = operandSeg(secondOperand);
		int twoNum = intConvert(two.substring(0, two.indexOf('/')));
		int twoDen = intConvert(two.substring(two.indexOf('/') + 1));

		if (operator.equals("+")) {
			String numerator = (oneNum * twoDen) + (twoNum * oneDen) + "";
			String denominator = oneDen * twoDen + "";
			String result = numerator + "/" + denominator;
			return oneNum+"";

		}
		if (operator.equals("-")) {
			String numerator = (oneNum * twoDen) - (twoNum * oneDen) + "";
			String denominator = oneDen * twoDen + "";
			String result = numerator + "/" + denominator;
			return result;
		}
		if (operator.equals("/")) {
			String numerator = oneNum * twoDen + "";
			String denominator = oneDen * twoNum + "";
			String result = numerator + "/" + denominator;
			return result;
		}
		if (operator.equals("*")) {
			String numerator = oneNum * twoNum + "";
			String denominator = oneDen * twoDen + "";
			String result = numerator + "/" + denominator;
			return result;
		} else {
			return "Input is in an invalid format.";
		}
		
	}
	*/

	public static String operandSeg(String operand) {
		String whole;
		String numerator;
		String denominator;
		// whole number if operand is a mixed number
		if (operand.indexOf('_') != -1) {
			whole = operand.substring(0, operand.indexOf('_'));
		} // whole number if operand is just a fraction
		else if (operand.indexOf('/') != -1) {
			whole = "0";
		} // whole number of operand is just a whole number
		else {
			whole = operand.substring(0, operand.length());
		}
		// numerator and denominator if operand is a fraction
		if (operand.indexOf('/') != -1) {
			numerator = operand.substring(operand.indexOf('_') + 1, operand.indexOf('/'));
			denominator = operand.substring(operand.indexOf('/') + 1);
		}
		// numerator and denominator if operand is a whole number
		else {
			numerator = "0";
			denominator = "1";
		}
		return ("whole:" + whole + " numerator:" + numerator + " denominator:" + denominator);
		/*
		// mixed number converter
		String newNumerator = intConvert(denominator) * intConvert(whole) + intConvert(numerator) + "";
		if (operand.substring(0,1).equals("-")) {
			String sign = "-";
			return sign + newNumerator + "/" + denominator;
		}
		else {
			String sign = "";
			return sign + newNumerator + "/" + denominator;
		}
		*/
	}

	public static int intConvert(String num) {
		for (int i = 0; i < 100; i++) {
			if (num.equals(i + "")) {
				return i;
			}
		}
		return 0;
	}

	/*public static String reducer(String input) {
		int numerator = intConvert(input.substring(0, input.indexOf("/")));
		int denominator = intConvert(input.substring(input.indexOf("/") + 1));
		if (denominator % numerator != 0) {
			for (int i = 2; i <= denominator; i++) {
				if ((numerator % i == 0) && (denominator % i == 0)) {
					int commonDen = i;
					String newNumerator = numerator / commonDen + "";
					String newDenominator = denominator / commonDen + "";
					// return newNumerator + "/" + newDenominator;
					return commonDen + "";
				}
			}
		}
		return "didn't work";
	}
	*/
	}
