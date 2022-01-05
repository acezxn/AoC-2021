import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Main {

	public static int partOne(String[] input) {
		int c = 0;
		for (String line : input) {
			String[] data = line.split(" \\| ");
			String[] back = data[1].split(" ");
			for (String b : back) {
				if (b.length() == 3 || b.length() == 7 || b.length() == 4 || b.length() == 2) 
				{
					c++;
				}
			}
		}
		return c;
	}

	public static ArrayList<String> checkOne(String[] data) {
		ArrayList<String> out = new ArrayList<String>();
		for (String s : data) {
			if (s.length() == 2) {
				out.add(s.substring(0,1));
				out.add(s.substring(1,2));
				return out;
			}
		}
		return out;
	}

	public static String checkSeven(String[] data, ArrayList<String> ONE) {
		for (String s : data) {
			if (s.length() == 3) {
				for (int i = 0; i < 3; i++) {
					if (!ONE.contains(s.substring(i, i+1))) {
						return s.substring(i, i+1);
					}
				}
			}
		}
		return "";
	}

	public static String[] checkThree(String[] data, ArrayList<String> ONE) {
		String[] out = new String[2];
		for (String s : data) {
			if (s.length() == 5) {
				int count = 0;
				for (int i = 0; i < 5; i++) {
					if (ONE.contains(s.substring(i, i+1))) {
						count++;
					}
				}
				if (count == 2) {
					int idx = 0;
					for (int i = 0; i < 7; i++) {
						String str = String.valueOf((char)('a' + i));
						boolean contains = false;
						for (int v = 0; v < s.length(); v++) {
							if (str.equals(s.substring(v, v+1))) {
								contains = true;
								break;
							}
						}
						if (!contains) {
							out[idx] = str;
							idx++;
						}
					}
				}
			}
		}
		return out;
	}

	public static String checkSix(String[] data, ArrayList<String> ONE) {
		for (String s : data) {
			if (s.length() == 6) {
				int contains_count = 0;
				String remainSegment = "";
				for (int i = 0; i < 6; i++) {
					if (ONE.contains(s.substring(i, i+1))) {
						contains_count++;
						remainSegment = s.substring(i, i+1);
					}
				}
				if (contains_count == 1) {
					int idx = ONE.indexOf(remainSegment) ^ 1;
					return ONE.get(idx);
				}
			}
		}
		return "";
	}

	public static String[] checkFour(String[] data, ArrayList<String> ONE) {
		String[] out = new String[2];
		int idx = 0;
		for (String s : data) {
			if (s.length() == 4) {
				for (int i = 0; i < 4; i++) {
					if (!ONE.contains(s.substring(i, i+1))) {
						out[idx] = s.substring(i, i+1);
						idx++;
					}
				}
			}
		}
		return out;
	}

	public static String checkEight(String[] data, ArrayList<String> oneToSix) {
		for (String s : data) {
			if (s.length() == 7) {
				for (int i = 0; i < 7; i++) {
					if (!oneToSix.contains(s.substring(i, i+1))) {
						return s.substring(i, i+1);
					}
				}
			}
		}
		return "";
	}


	public static long partTwo(String[] input) {
		// parse input
		long total = 0;
		for (String line : input) {
			long sum = 0;
			String[] data = line.split(" \\| ");
			String[] front = data[0].split(" ");
			String[] back = data[1].split(" ");
			/*
					11111
				 2     3
				 2     3
				  44444
				 5     6
				 5     6
				  77777
			*/

			ArrayList<String> ONE = checkOne(front); // returns position [3, 6]
			String one = checkSeven(front, ONE); // returns position 1
			String three = checkSix(front, ONE); // returns position 3
			String[] twoOrFour = checkFour(front, ONE); // returns position [2, 4]
			String[] twoOrFive = checkThree(front, ONE); // returns position [2, 5]
			String two = "";
			int tfoTwoIdx = -1;
			int tfiTwoIdx = -1;
			for (int i = 0; i < 2; i++) {
				String tfi = twoOrFive[i];
				for (int j = 0; j < 2; j++) {
					String tfo = twoOrFour[j];
					if (tfo.equals(tfi)) {
						two = tfo;
						tfoTwoIdx = j;
						tfiTwoIdx = i;
					}
				}
			}
			String five = twoOrFive[tfiTwoIdx ^ 1];
			String four = twoOrFour[tfoTwoIdx ^ 1];
			String six = "";
			for (int i = 0; i < 2; i++) {
				if (!ONE.get(i).equals(three)) {
					six = ONE.get(i);
				}
			}
			ArrayList<String> oneToSix = new ArrayList<String>();
			oneToSix.add(one);
			oneToSix.add(two);
			oneToSix.add(three);
			oneToSix.add(four);
			oneToSix.add(five);
			oneToSix.add(six);
			String seven = checkEight(front, oneToSix); // returns position 7

			// declare integers from ZERO to NINE, sort them
			// check back
			ArrayList<String> ZERO = new ArrayList<String>();
			ZERO.add(one);
			ZERO.add(two);
			ZERO.add(three);
			ZERO.add(five);
			ZERO.add(six);
			ZERO.add(seven);
			ArrayList<String> TWO = new ArrayList<String>();
			TWO.add(one);
			TWO.add(three);
			TWO.add(four);
			TWO.add(five);
			TWO.add(seven);
			ArrayList<String> THREE = new ArrayList<String>();
			THREE.add(one);
			THREE.add(three);
			THREE.add(four);
			THREE.add(six);
			THREE.add(seven);
			ArrayList<String> FOUR = new ArrayList<String>();
			FOUR.add(two);
			FOUR.add(three);
			FOUR.add(four);
			FOUR.add(six);
			ArrayList<String> FIVE = new ArrayList<String>();
			FIVE.add(one);
			FIVE.add(two);
			FIVE.add(four);
			FIVE.add(six);
			FIVE.add(seven);
			ArrayList<String> SIX = new ArrayList<String>();
			SIX.add(one);
			SIX.add(two);
			SIX.add(four);
			SIX.add(five);
			SIX.add(six);
			SIX.add(seven);
			ArrayList<String> SEVEN = new ArrayList<String>();
			SEVEN.add(three);
			SEVEN.add(six);
			SEVEN.add(one);
			ArrayList<String> EIGHT = new ArrayList<String>();
			EIGHT.add(one);
			EIGHT.add(two);
			EIGHT.add(three);
			EIGHT.add(four);
			EIGHT.add(five);
			EIGHT.add(six);
			EIGHT.add(seven);
			ArrayList<String> NINE = new ArrayList<String>();
			NINE.add(one);
			NINE.add(two);
			NINE.add(three);
			NINE.add(four);
			NINE.add(six);
			NINE.add(seven);

			// Collections.sort(ONE);
			// Collections.sort(TWO);
			// Collections.sort(THREE);
			// Collections.sort(FOUR);
			// Collections.sort(FIVE);
			// Collections.sort(SIX);
			// Collections.sort(SEVEN);
			// Collections.sort(EIGHT);
			// Collections.sort(NINE);
			for (String b : back) {
				sum *= 10;
				if (b.length() == 2) {
					sum += 1;
				} 
				else if (b.length() == 4) {
					sum += 4;
				}
				else if (b.length() == 3) {
					sum += 7;
				} 
				else if (b.length() == 7) {
					sum += 8;
				}
				else if (b.length() == 5) {
					boolean isTwo = true;
					boolean isFive = true;
					boolean isThree = true;
					for (int i = 0; i < 5; i++) {
						if (!TWO.contains(b.substring(i, i+1))) {
							isTwo = false;
						}
						if (!THREE.contains(b.substring(i, i+1))) {
							isThree = false;
						}
						if (!FIVE.contains(b.substring(i, i+1))) {
							isFive = false;
						}
					}
					if (isTwo) {
						sum += 2;
					}
					else if (isFive) {
						sum += 5;
					}
					else if (isThree) {
						sum += 3;
					}
				}
				else if (b.length() == 6) {
					boolean isSix = true;
					boolean isNine = true;
					boolean isZero = true;
					for (int i = 0; i < 6; i++) {
						if (!SIX.contains(b.substring(i, i+1))) {
							isSix = false;
						}
						if (!NINE.contains(b.substring(i, i+1))) {
							isNine = false;
						}
						if (!ZERO.contains(b.substring(i, i+1))) {
							isZero = false;
						}

					}
					if (isSix) {
						sum += 6;
					}
					else if (isNine) {
						sum += 9;
					}

				}
				
				
			}
			total += sum;
		}
		return total;
	}

	public static void main(String[] args) {
		try {
			File f = new File("input.txt");
			Scanner reader = new Scanner(f);
			String input = "";
			while (reader.hasNextLine()) {
				input += reader.nextLine() + "\n";
			}
			String[] inp = input.split("\n");
			System.out.println("Part one: " + partOne(inp));
			System.out.println("Part two: " + partTwo(inp));

		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
}
