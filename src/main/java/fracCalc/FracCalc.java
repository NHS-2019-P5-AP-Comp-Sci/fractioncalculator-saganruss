/**
 * @author Mr. Rasmussen
 */

package fracCalc;

import java.util.*;

public class FracCalc {

	public static void main(String[] args) {
		// produceAnswer method is applied to user input until "quit"
		Scanner userInput = new Scanner(System.in);
		String equation = userInput.nextLine();
		while (!equation.equals("quit")) {
			System.out.println(produceAnswer(equation));
			equation = userInput.nextLine();
		}
		userInput.close();
	}

	public static String produceAnswer(String input) {
		// input segmentation into operands and operator
		int spaceIndex = input.indexOf(' ');
		String firstOperand = input.substring(0, spaceIndex);
		String operator = input.substring(input.indexOf(" ") + 1, spaceIndex + 2);
		String secondOperand = input.substring(spaceIndex + 3);

		// operand segmentation into numerator and denominator
		String one = operandSeg(firstOperand);
		int oneNum = Integer.parseInt(one.substring(0, one.indexOf('/')));
		int oneDen = Integer.parseInt(one.substring(one.indexOf('/') + 1));
		String two = operandSeg(secondOperand);
		int twoNum = Integer.parseInt(two.substring(0, two.indexOf('/')));
		int twoDen = Integer.parseInt(two.substring(two.indexOf('/') + 1));

		// addition
		if (operator.equals("+")) {
			String numerator = (oneNum * twoDen) + (twoNum * oneDen) + "";
			String denominator = oneDen * twoDen + "";
			String result = numerator + "/" + denominator;
			return reducer(result);

			// subtraction
		} else if (operator.equals("-")) {
			String numerator = (oneNum * twoDen) - (twoNum * oneDen) + "";
			String denominator = oneDen * twoDen + "";
			String result = numerator + "/" + denominator;
			return reducer(result);

			// division
		} else if (operator.equals("/")) {
			if (oneNum == 0 || oneDen == 0 || twoNum == 0 || twoDen == 0)
				return "0";
			String numerator = oneNum * twoDen + "";
			String denominator = oneDen * twoNum + "";
			String result = numerator + "/" + denominator;
			return reducer(result);

			// multiplication
		} else if (operator.equals("*")) {
			if (oneNum == 0 || oneDen == 0 || twoNum == 0 || twoDen == 0)
				return "0";
			String numerator = oneNum * twoNum + "";
			String denominator = oneDen * twoDen + "";
			String result = numerator + "/" + denominator;
			return reducer(result);

		} else {
			return "ERROR: Input is in an invalid format.";
		}

	}

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

		// mixed number converter
		if (whole.length() > 0) {
			if (whole.substring(0, 1).equals("-")) {
				numerator = "-" + (Integer.parseInt(denominator) * Integer.parseInt(whole.substring(1))
						+ Integer.parseInt(numerator));
			} else if (!whole.substring(0, 1).equals("-")) {
				numerator = Integer.parseInt(denominator) * Integer.parseInt(whole) + Integer.parseInt(numerator) + "";
			}
		}
		return numerator + "/" + denominator;
	}

	public static String reducer(String input) {
		// output segmentation into numerator, denominator and whole number
		int numerator = Integer.parseInt(input.substring(0, input.indexOf("/")));
		int denominator = Integer.parseInt(input.substring(input.indexOf("/") + 1));
		int whole = numerator / denominator;

		// adjusts position of negative sign
		if ((numerator < 0 && denominator < 0) || (numerator > 0 && denominator < 0)) {
			numerator /= -1;
			denominator /= -1;
		}

		// simplifies outputs equal to 0
		if (numerator == 0 || denominator == 0)
			return "0";

		// finds and divides by common denominator
		for (int i = Math.abs(denominator); i > 0; i--) {
			if ((numerator % i == 0) && (denominator % i == 0)) {
				numerator = numerator / i;
				denominator = denominator / i;
			}
		}

		// simplifies fractions over 1
		if (denominator == 1) {
			return numerator + "";

			// simplifies fractions equal to a whole number
		} else if (numerator % denominator == 0 && denominator != 0) {
			return (numerator / denominator) + "";
		}

		// outputs simplified fractions and mixed numbers
		else {
			numerator = numerator - (whole * denominator);
			if (whole == 0) {
				return numerator + "/" + denominator;
			} else {
				if (whole < 0 && denominator < 0)
					denominator /= -1;
				else if (whole < 0 && numerator < 0)
					numerator /= -1;
				return "" + whole + "_" + numerator + "/" + denominator;
			}
		}

	}
}
