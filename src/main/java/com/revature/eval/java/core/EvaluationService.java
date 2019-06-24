package com.revature.eval.java.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EvaluationService {

	/**
	 * 1. Convert a phrase to its acronym. Techies love their TLA (Three Letter
	 * Acronyms)! Help generate some jargon by writing a program that converts a
	 * long name like Portable Network Graphics to its acronym (PNG).
	 * 
	 * @param phrase
	 * @return
	 */
	public String acronym(String phrase) {
		// TODO Write an implementation for this method declaration
		char[] acr = new char[phrase.length()];
		char[] acroo = phrase.toCharArray();
		int j = 1;
		phrase.getChars(0, 1, acr, 0);
		for (int i=0; i<acroo.length; i++) {
			String oneletter=String.valueOf(acroo[i]);
			if (oneletter.equals(" ")) { 
				phrase.getChars(i+1,i+2,acr,j);
				j+=1;
			}
			if (oneletter.equals("-")) {
				phrase.getChars(i+1,i+2,acr,j);
				j+=1;
			}
		}
		String acronym=new String(acr);
		return acronym.toUpperCase();
		}
		

	/**
	 * 2. Given a word, compute the scrabble score for that word.
	 * 
	 * --Letter Values-- Letter Value A, E, I, O, U, L, N, R, S, T = 1; D, G = 2; B,
	 * C, M, P = 3; F, H, V, W, Y = 4; K = 5; J, X = 8; Q, Z = 10; Examples
	 * "cabbage" should be scored as worth 14 points:
	 * 
	 * 3 points for C, 1 point for A, twice 3 points for B, twice 2 points for G, 1
	 * point for E And to total:
	 * 
	 * 3 + 2*1 + 2*3 + 2 + 1 = 3 + 2 + 6 + 3 = 5 + 9 = 14
	 * 
	 * @param string
	 * @return
	 */
	public int getScrabbleScore(String string) {
		// TODO Write an implementation for this method declaration
		string=string.toUpperCase();
		char[] word=string.toCharArray();
		int score=0;
		
		for (char letter: word) {
			score+= values(letter);
		}
		return score;
			}

		private int values(char letter) {
			switch (letter){
            case 'G':
            case 'D': return 2;

            case 'B':
            case 'C':
            case 'M':
            case 'P': return 3;

            case 'F':
            case 'H':
            case 'V':
            case 'W':
            case 'Y': return 4;

            case 'K': return 5;

            case 'J':
            case 'X': return 8;

            case 'Q':
            case 'Z': return 10;

            default: return 1;
	}
		}

	/**
	 * 3. Clean up user-entered phone numbers so that they can be sent SMS messages.
	 * 
	 * The North American Numbering Plan (NANP) is a telephone numbering system used
	 * by many countries in North America like the United States, Canada or Bermuda.
	 * All NANP-countries share the same international country code: 1.
	 * 
	 * NANP numbers are ten-digit numbers consisting of a three-digit Numbering Plan
	 * Area code, commonly known as area code, followed by a seven-digit local
	 * number. The first three digits of the local number represent the exchange
	 * code, followed by the unique four-digit number which is the subscriber
	 * number.
	 * 
	 * The format is usually represented as
	 * 
	 * 1 (NXX)-NXX-XXXX where N is any digit from 2 through 9 and X is any digit
	 * from 0 through 9.
	 * 
	 * Your task is to clean up differently formatted telephone numbers by removing
	 * punctuation and the country code (1) if present.
	 * 
	 * For example, the inputs
	 * 
	 * +1 (613)-995-0253 613-995-0253 1 613 995 0253 613.995.0253 should all produce
	 * the output
	 * 
	 * 6139950253
	 * 
	 * Note: As this exercise only deals with telephone numbers used in
	 * NANP-countries, only 1 is considered a valid country code.
	 */
	public String cleanPhoneNumber(String string) {
		// TODO Write an implementation for this method declaration
		String dash = "-";
		String dot = ".";
		String space = " ";
		String brac = "(";
		String bracs = ")";
		ArrayList<Character> numbers = new ArrayList<Character>();
		for (int i=0;i<string.length();i++) {
			if (Character.isDigit(string.charAt(i))) {
				if (!(string.charAt(i)==dot.charAt(0))) {
					if (!(string.charAt(i)==space.charAt(0))) {
						numbers.add(string.charAt(i));
					}
				}
			}
			if (!(Character.isDigit(string.charAt(i)))) {
				if (!(string.charAt(i)==dash.charAt(0))) {
					if (!(string.charAt(i)==dot.charAt(0))) {
						if (!(string.charAt(i)==space.charAt(0))) {
							if (!(string.charAt(i)==brac.charAt(0))) {
								if (!(string.charAt(i)==bracs.charAt(0))) {
									throw new IllegalArgumentException();
								}
							}
						}
					}
				}
			}
		}
		if(numbers.get(0)==Character.forDigit(1, 10)) {
			numbers.remove(0);
		}
		if(!(numbers.size()==10)) {
			throw new IllegalArgumentException();
		}
		StringBuilder number = new StringBuilder();
		for (Character ch : numbers) {
			number.append(ch);
		}
		
		return number.toString();
	}

	/**
	 * 4. Given a phrase, count the occurrences of each word in that phrase.
	 * 
	 * For example for the input "olly olly in come free" olly: 2 in: 1 come: 1
	 * free: 1
	 * 
	 * @param string
	 * @return
	 */
	public Map<String, Integer> wordCount(String string) {
		// TODO Write an implementation for this method declaration
		Map <String, Integer> dict = new HashMap<>();
		String [] splitt = string.split(" |\\,");
		
		for (String word : splitt) {
			if (dict.containsKey(word)) {
				int value = dict.get(word);
				value+=1;
				dict.put(word, value);
			}
			else {
				dict.put(word, 1);
			}
		}
		
		return dict;
	}

	/**
	 * 5. Implement a binary search algorithm.
	 * 
	 * Searching a sorted collection is a common task. A dictionary is a sorted list
	 * of word definitions. Given a word, one can find its definition. A telephone
	 * book is a sorted list of people's names, addresses, and telephone numbers.
	 * Knowing someone's name allows one to quickly find their telephone number and
	 * address.
	 * 
	 * If the list to be searched contains more than a few items (a dozen, say) a
	 * binary search will require far fewer comparisons than a linear search, but it
	 * imposes the requirement that the list be sorted.
	 * 
	 * In computer science, a binary search or half-interval search algorithm finds
	 * the position of a specified input value (the search "key") within an array
	 * sorted by key value.
	 * 
	 * In each step, the algorithm compares the search key value with the key value
	 * of the middle element of the array.
	 * 
	 * If the keys match, then a matching element has been found and its index, or
	 * position, is returned.
	 * 
	 * Otherwise, if the search key is less than the middle element's key, then the
	 * algorithm repeats its action on the sub-array to the left of the middle
	 * element or, if the search key is greater, on the sub-array to the right.
	 * 
	 * If the remaining array to be searched is empty, then the key cannot be found
	 * in the array and a special "not found" indication is returned.
	 * 
	 * A binary search halves the number of items to check with each iteration, so
	 * locating an item (or determining its absence) takes logarithmic time. A
	 * binary search is a dichotomic divide and conquer search algorithm.
	 * 
	 */
	static class BinarySearch<T> {
		private List<T> sortedList;

		public int indexOf(T t) {
			// TODO Write an implementation for this method declaration
			
			return 0;
		}

		public BinarySearch(List<T> sortedList) {
			super();
			this.sortedList = sortedList;
		}

		public List<T> getSortedList() {
			return sortedList;
		}

		public void setSortedList(List<T> sortedList) {
			this.sortedList = sortedList;
		}

	}

	/**
	 * 6. An Armstrong number is a number that is the sum of its own digits each
	 * raised to the power of the number of digits.
	 * 
	 * For example:
	 * 
	 * 9 is an Armstrong number, because 9 = 9^1 = 9 10 is not an Armstrong number,
	 * because 10 != 1^2 + 0^2 = 2 153 is an Armstrong number, because: 153 = 1^3 +
	 * 5^3 + 3^3 = 1 + 125 + 27 = 153 154 is not an Armstrong number, because: 154
	 * != 1^3 + 5^3 + 4^3 = 1 + 125 + 64 = 190 Write some code to determine whether
	 * a number is an Armstrong number.
	 * 
	 * @param input
	 * @return
	 */
	
	int power(int a, int b) {
	
		if(b==0) {
			return 1;
		}
		 if (b%2 == 0) {
	            return power(a, b/2)*power(a, b/2); 
		 }
		return a*power(a, b/2)*power(a, b/2);
	}
	
	public boolean isArmstrongNumber(int input) {
		// TODO Write an implementation for this method declaration
	
		int digit = String.valueOf(input).length();
		int clone = input;
		int total = 0;
		while (clone!=0) {
			int count = clone%10;
			total += power(count,digit);
			clone /=10;
		}
		if (total==input) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * 7. Compute the prime factors of a given natural number.
	 * 
	 * A prime number is only evenly divisible by itself and 1.
	 * 
	 * Note that 1 is not a prime number.
	 * 
	 * @param l
	 * @return
	 */
	public List<Long> calculatePrimeFactorsOf(long l) {
		// TODO Write an implementation for this method declaration
		List<Long> longlist = new ArrayList<Long>();
		while (l%2==0) {
			longlist.add(2L);
		}
		for (int i = 3; i < l; i++) {
			while (l%i==0) {
				longlist.add((long) i);
				l /= i;
			}
		}
		
	
		return longlist;
	}


	/**
	 * 8-9. Create an implementation of the atbash cipher, an ancient encryption
	 * system created in the Middle East.
	 * 
	 * The Atbash cipher is a simple substitution cipher that relies on transposing
	 * all the letters in the alphabet such that the resulting alphabet is
	 * backwards. The first letter is replaced with the last letter, the second with
	 * the second-last, and so on.
	 * 
	 * An Atbash cipher for the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: zyxwvutsrqponmlkjihgfedcba It is a
	 * very weak cipher because it only has one possible key, and it is a simple
	 * monoalphabetic substitution cipher. However, this may not have been an issue
	 * in the cipher's time.
	 * 
	 * Ciphertext is written out in groups of fixed length, the traditional group
	 * size being 5 letters, and punctuation is excluded. This is to make it harder
	 * to guess things based on word boundaries.
	 * 
	 * Examples Encoding test gives gvhg Decoding gvhg gives test Decoding gsvjf
	 * rxpyi ldmul cqfnk hlevi gsvoz abwlt gives thequickbrownfoxjumpsoverthelazydog
	 *
	 */
	static class AtbashCipher {

		/**
		 * Question 8
		 * 
		 * @param string
		 * @return
		 */
		public static String encode(String string) {
			// TODO Write an implementation for this method declaration
			string = string.toLowerCase();
			ArrayList<String> encrypted = new ArrayList<>();
			String alphabet = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z";
			ArrayList<String> order = new ArrayList<>(Arrays.asList(alphabet.split(",")));
			String space = " ";
			String dot = ".";
			int count = 0;
			for (char c : string.toCharArray()) {
				int position = 0;
				if (Character.isLetter(c)) {
					position = order.indexOf(String.valueOf(c));
					position = 25-position;
					System.out.println(count);
					if (count%5==0 && count!=0) {
						encrypted.add(" ");
						System.out.println("hey");
						count=1;
					}
					else {
						count += 1;
					}
					encrypted.add(order.get(position));
				}
				else if (Character.isDigit(c) && c!=space.charAt(0) && c!=dot.charAt(0)) {
					position = order.indexOf(String.valueOf(c));
					position = 25-position;
					System.out.println(count);
					if (count%5==0 && count!=0) {
						encrypted.add(" ");
						System.out.println("hey");
						count=1;
					}
					else {
						count += 1;
					}
					encrypted.add(Character.toString(c));
				}
		}
			
			return String.join("",encrypted);
		}

		/**
		 * Question 9
		 * 
		 * @param string
		 * @return
		 */
		public static String decode(String string) {
			// TODO Write an implementation for this method declaration
			ArrayList<String> decrypted = new ArrayList<>();
			String alphabet = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z";
			ArrayList<String> order = new ArrayList<>(Arrays.asList(alphabet.split(",")));
			String space = " ";
			for (char c : string.toCharArray()) {
				if(Character.isDigit(c)) {
					decrypted.add(Character.toString(c));
				}
				else if (c!=space.charAt(0)) {
					int position = 0;
					position = order.indexOf(String.valueOf(c));
					position = 25-position;
					decrypted.add(order.get(position));
				}
			}
			return String.join("",decrypted);
		}
	}

	/**
	 * 10. Parse and evaluate simple math word problems returning the answer as an
	 * integer.
	 * 
	 * Add two numbers together.
	 * 
	 * What is 5 plus 13?
	 * 
	 * 18
	 * 
	 * Now, perform the other three operations.
	 * 
	 * What is 7 minus 5?
	 * 
	 * 2
	 * 
	 * What is 6 multiplied by 4?
	 * 
	 * 24
	 * 
	 * What is 25 divided by 5?
	 * 
	 * 5
	 * 
	 * @param string
	 * @return
	 */
	public int solveWordProblem(String string) {
		// TODO Write an implementation for this method declaration
		String space = " ";
		int count=0;
		int a = 0;
		int b = 0;
		int p = 0;
		int mi = 0;
		int mu = 0;
		int d = 0;
		for (char c : string.toCharArray()) {
			if (c==space.charAt(0)) {
				count+=1;
			}
			if (count==2) {
				a = Character.getNumericValue(c);
				count+=1;
			}
			if (count==3) {
				if (String.valueOf(c)=="p") {
					p = 1;
					count+=1;
				}
				if (String.valueOf(c)=="m") {
					mi+=1;
					mu+=1;
				}
				if (String.valueOf(c)=="d") {
					d = 1;
					count+=2;
				}
				if (String.valueOf(c)=="i") {
					mi+=1;
					count+=2;
				}
				if (String.valueOf(c)=="u") {
					mu+=1;
					count+=1;
				}
			}
			if (count==4) {
				b = Character.getNumericValue(c);
				count+=1;
			}
		}
		if(p==1) {
			return a+b;
		}
		else if (mi==2) {
			return a-b;
		}
		else if (mu==2) {
			return a*b;
		}
		else{
			return a/b;
		}
	}

}
