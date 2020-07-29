package converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

public class Converter {
	public static String convertNumber(double decimal, int base) {
		//Only accept bases of 2 and 10 (binary and decimal)
		if (base != 2 && base != 10) {
			JOptionPane.showConfirmDialog(null, null, "Please provide a valid base", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
			System.out.println("Please provide a valid base");
			System.exit(0);
		}
		
		//Special cases
		if (decimal == 1) return "1";
		if (decimal == 0) return "0";
		
		//If we want to convert to binary, the actual binary number will be calculated using powers of 10
		int refBase = 10;
		//If we want to convert to decimal, the actual decimal number will be calculated using powers of 2
		if (base == 10) {
			refBase = 2;
			if (decimal % 10 != 0 && (decimal - 1) % 10 != 0) {
				JOptionPane.showConfirmDialog(null, null, "Number provided is not binary", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				System.out.println("Number provided is not binary");
				System.exit(0);
			}
		}
		
		double divisionResult = decimal;
		double number = decimal;
		int divisions = 0;
		List<Integer> powersOfBase = new ArrayList<Integer>(); //List of powers of two found
						
		while (number > 1) {
			while (divisionResult > 1) {
				divisionResult=divisionResult/base;
//				System.out.println(divisionResult);
				divisions++;
			}
			/*If the result of the last division is 1, then 'number' is a power of 2
			 * and we don't have to subtract 1 from divisions
			 */
			if (divisionResult == 1) {
				powersOfBase.add(divisions);
				break;
			}
			powersOfBase.add(divisions - 1); //Storing powers of two found in the array list
			/*Subtracting the power of two found from the number given
			 * until we either find 1 or 0 and end this block of code */
			number = number - Math.pow(base, divisions - 1);
			divisionResult = number;
			divisions = 0; //Reinitializing the number of divisions made
		}
		
		if (number == 1) powersOfBase.add(0); //Adding 1 in binary base so we can represent odd numbers
		System.out.println(powersOfBase);
		
		if (base == 2) {
			char[] response = new char[powersOfBase.get(0)+1];
			Arrays.fill(response, '0');
			
			//Setting the appropriate numbers to 1
			for (int power: powersOfBase) {
				response[power] = '1';
			}
			
			//Reversing the array
			char aux = '0';
			for (int i=0; i<response.length/2; i++) {
				aux = response[i];
				response[i] = response[response.length - 1 - i];
				response[response.length - 1 - i] = aux;
			}
			System.out.println(String.valueOf(response));
			return String.valueOf(response);
		}
		
		double result = 0;
		
		//Doesn't work when result is int and we want to convert number>1023 to binary
		//So we'll only use this method to convert to decimal now
		for (int power: powersOfBase) {
			result = result + Math.pow(refBase, power);
		}
		
		//Will only return numbers converted to decimal
		String dec = new BigDecimal(result).toPlainString();
		
		return dec;
	}
}
