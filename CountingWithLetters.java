package countingWithLetters;

import java.math.BigInteger;
import java.util.Scanner;

public class CountingWithLetters {

	static String CURRENT_POSITION = "ROP"; //Change the value in quotation marks to whatever the latest count was to get accurate results.

	public static void main(String[] args) {
		boolean exit = false;
		System.out.println("What was the last count?");
		CURRENT_POSITION = readInput();
		
		final BigInteger CURRENT_INT = stringToInt(CURRENT_POSITION);
		
		while (exit == false) {

			System.out.println("Please enter a string:");
			String word = readInput(); //returns input.toUpperCase().trim()
			
			if (word.length() < 15){
				
				BigInteger wordInt = stringToInt(word); // "convert the string to an int";
				BigInteger distance = wordInt.subtract(CURRENT_INT);
	
				// CWL started Tuesday November 25th 18:54:27
				// cpm as of 29th May 2014 - 0.0471820323
	
				//final double AVG_COUNTS_PER_MINUTE = 0.0471820323; // equals 47182 / 1000000
				BigInteger minutes = BigInteger.valueOf(0);
	
				// cpm = counts / minutes
				
				if (distance.compareTo(BigInteger.valueOf(0)) < 0) {
					System.out.println("According to my records, you've already passed that string! Yay! :D");
				} else {
					minutes = distance.multiply(BigInteger.valueOf(1000000)).divide(BigInteger.valueOf(47182));
					System.out.println("You'll get to that string in " + distance + " counts, which will take approximately " + minutesToTime(minutes) + ".");
				}
			} else {
				System.out.println("Oops! That string is too long! Please try a shorter one!");
			}
		}
	}

	private static BigInteger stringToInt(String input) { // tested, seems to work
		int power = 0;
		BigInteger total1 = BigInteger.valueOf(0);

		for (int i = input.length() - 1; i >= 0; i--) {
			total1 = total1.add(BigInteger.valueOf(charToInt(input.charAt(i)) * ((long) Math.pow(26, power))));
			power++;
		}
		return total1;
	}

	private static byte charToInt(char input) {
		switch (input) {
			case 'A': return 1;
			case 'B': return 2;
			case 'C': return 3;
			case 'D': return 4;
			case 'E': return 5;
			case 'F': return 6;
			case 'G': return 7;
			case 'H': return 8;
			case 'I': return 9;
			case 'J': return 10;
			case 'K': return 11;
			case 'L': return 12;
			case 'M': return 13;
			case 'N': return 14;
			case 'O': return 15;
			case 'P': return 16;
			case 'Q': return 17;
			case 'R': return 18;
			case 'S': return 19;
			case 'T': return 20;
			case 'U': return 21;
			case 'V': return 22;
			case 'W': return 23;
			case 'X': return 24;
			case 'Y': return 25;
			case 'Z': return 26;
			default: return -1;
		}
	}

	public static String readInput() {
		String input = "";
		boolean exit = false;
		
		Scanner scanner = new Scanner(System.in);
		
		while (exit==false){
			input = scanner.nextLine().trim().toUpperCase();
			if (input.matches("[ABCDEFGHIJKLMNOPQRSTUVWXYZ]*") == false){
				System.out.println("Invalid input - please enter only alphabetic letters. Try entering a different string.");
			} else {
				exit = true;
			}
		}

		return input;
	}

	public static String minutesToTime(BigInteger minutesBig) {
	
		//WARNING! Reading this function will turn your brain to jelly. Proceed at your own risk.
		
		BigInteger hours = minutesBig.divide(BigInteger.valueOf(60));
		BigInteger days = hours.divide(BigInteger.valueOf(24));
		BigInteger weeks = days.divide(BigInteger.valueOf(7));
		BigInteger years = weeks.divide(BigInteger.valueOf(52));
		
		if (hours.compareTo(BigInteger.valueOf(0)) == 0) {
			return minutesBig.toString() + " minutes";
		} else if (days.compareTo(BigInteger.valueOf(0)) == 0) { // number of minutes in a day
			return (minutesBig.divide(BigInteger.valueOf(60)).toString() + (minutesBig.divide(BigInteger.valueOf(60)).compareTo(BigInteger.valueOf(1)) == 0 ? " hour and " : " hours and ") + (minutesBig.mod(BigInteger.valueOf(60)).toString() + " minutes"));
		} else if (weeks.compareTo(BigInteger.valueOf(0)) == 0) { // number of minutes in a week
			return (minutesBig.mod(BigInteger.valueOf(60 * 24)).compareTo(BigInteger.valueOf(0)) > 0) ? minutesBig.divide(BigInteger.valueOf(60 * 24)).toString()
					+ (minutesBig.divide(BigInteger.valueOf(60 * 24)).compareTo(BigInteger.valueOf(1)) == 0 ? " day and " : " days and ")
					+ (minutesBig.divide(BigInteger.valueOf(60)).mod(BigInteger.valueOf(24)).toString())
					+ (minutesBig.divide(BigInteger.valueOf(60)).mod(BigInteger.valueOf(24)).compareTo(BigInteger.valueOf(1))== 0 ? " hour" : " hours")
					: minutesBig.divide(BigInteger.valueOf(60 * 24)).toString() + (minutesBig.divide(BigInteger.valueOf(60 * 24)).compareTo(BigInteger.valueOf(1))== 0 ? " day" : " days");
		} else if (years.compareTo(BigInteger.valueOf(0)) == 0) { // number of minutes in a year
			return (minutesBig.mod(BigInteger.valueOf(60 * 24 * 7)).compareTo(BigInteger.valueOf(0)) > 0) ? minutesBig.divide(BigInteger.valueOf(60 * 24 * 7))
					+ (minutesBig.divide(BigInteger.valueOf(60 * 24 * 7)).compareTo(BigInteger.valueOf(1)) == 0 ? " week and "
					: " weeks and ") + (minutesBig.divide(BigInteger.valueOf(60 * 24))).mod(BigInteger.valueOf(7))
					+ ((minutesBig.divide(BigInteger.valueOf(60 * 24)).mod(BigInteger.valueOf(7)).compareTo(BigInteger.valueOf(1))) == 0 ? " day" : " days")
					: minutesBig.divide(BigInteger.valueOf(60 * 24 * 7)) 
					+ (minutesBig.divide(BigInteger.valueOf(60 * 24 * 7)).compareTo(BigInteger.valueOf(1)) == 0 ? " week" : " weeks");
		} else {
			return years.toString() + (years.compareTo(BigInteger.valueOf(1)) == 0 ? " year and " : " years and ")
					+ minutesBig.divide(BigInteger.valueOf(60 * 24 * 7)).mod(BigInteger.valueOf(52))
					+ (minutesBig.divide(BigInteger.valueOf(60 * 24 * 7)).mod(BigInteger.valueOf(52)).compareTo(BigInteger.valueOf(1)) == 0 ? " week" : " weeks");
		}
		
		//Alternative code for minutesToTime with no bigIntegers. limited to 7 character input strings.
		
		/*int minutes = minutesBig.intValue();
		
		int hours = (int) Math.floor(minutes / 60);
		int days = (int) Math.floor(hours / 24);
		int weeks = (int) Math.floor(days / 7);
		int years = (int) Math.floor(weeks / 52);

		if (hours == 0) {
			return minutes + " minutes";
		} else if (days == 0) { // number of minutes in a day
			return ((minutes % 60 > 0) ? minutes / 60
					+ (minutes / 60 == 1 ? " hour and " : " hours and ")
					+ (minutes % 60) + " minutes" : minutes / 60 + " hours");
		} else if (weeks == 0) { // number of minutes in a week
			return (minutes % (60 * 24) > 0) ? minutes / (60 * 24)
					+ (minutes / (60 * 24) == 1 ? " day and " : " days and ")
					+ (minutes / 60) % 24
					+ ((minutes / 60) % 24 == 1 ? " hour" : " hours") : minutes
					/ (60 * 24) + (minutes / (60 * 24) == 1 ? " day" : " days");
		} else if (years == 0) { // number of minutes in a year
			return (minutes % (60 * 24 * 7) > 0) ? minutes
					/ (60 * 24 * 7)
					+ (minutes / (60 * 24 * 7) == 1 ? " week and "
							: " weeks and ") + (minutes / (60 * 24)) % 7
					+ ((minutes / (60 * 24)) % 7 == 1 ? " day" : " days")
					: minutes
							/ (60 * 24 * 52)
							+ (minutes / (60 * 24 * 52) == 1 ? " week"
									: " weeks");
		} else {
			return years + (years == 1 ? " year and " : " years and ")
					+ minutes / (60 * 24 * 7) % 52
					+ (minutes / (60 * 24 * 7) % 52 == 1 ? " week" : " weeks");
		}*/
	}

}
