import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Main {




	public static int MCBString(String input) {
		String[] bytes = input.split("\n");
		int length = bytes[0].length();
		int[] gammaMap = new int[length];
		int[] epsilonMap = new int[length];
		int gamma = 0;
		int epsilon = 0;
		for (int i = 0; i < length; i++) {
			gammaMap[i] = 0;
			epsilonMap[i] = 0;
		}
		for (String b : bytes) {
			for (int i = 0; i < length; i++) {
				if (b.charAt(i) == '1') {
					gammaMap[i] += 1;
					epsilonMap[i] -= 1;
				} else {
					gammaMap[i] -= 1;
					epsilonMap[i] += 1;
				}
			}
		}
		for (int i = 0; i < length; i++) {
			if (gammaMap[i] > 0) {
				gamma += Math.pow(2, length-1-i);
			}
			if (epsilonMap[i] > 0) {
				epsilon += Math.pow(2, length-1-i);
			}
		}
		return gamma * epsilon;

	}

	public static String getMostCommon(String[] input, int idx) {
		int n = 0;
		for (String s : input) {
			if (!s.equals("")){
				if (s.charAt(idx) == '1') {
					n += 1;
				} else {
					n -= 1;
				}
			}
		}
		if (n > 0) {
			return "1";
		}
		else if (n < 0) {
			return "0";
		} 
		else {
			return "1";
		}
	}

	public static String getLeastCommon(String[] input, int idx) {
		int n = 0;
		for (String s : input) {
			if (!s.equals("")){
				if (s.charAt(idx) == '1') {
					n += 1;
				} else {
					n -= 1;
				}
			}
		}
		if (n > 0) {
			return "0";
		}
		else if (n < 0) {
			return "1";
		} 
		else {
			return "0";
		}
	}

	public static int getOxygen(String input) {
		String[] bytes = input.split("\n");
		int length = bytes[0].length();
		for (int i = 0; i < length; i++) {
			String mc = getMostCommon(bytes, i);
			String buff = "";
			for (int j = 0; j < bytes.length; j++) {
				if (bytes[j].length() != 0) {
					if (bytes[j].substring(i, i+1).equals(mc)) {
						buff += bytes[j];
						buff += " ";
					}
				}
			}
			bytes = buff.split(" ");
		}

		int num = 0;
		for (int i = 0; i < length; i++) {
			if (bytes[0].charAt(i) == '1') {
				num += Math.pow(2, length-1-i);
			}
		}
		return num;
	}

	public static int getCarbon(String input) {
		String[] bytes = input.split("\n");
		int length = bytes[0].length();
		for (int i = 0; i < length; i++) {
			if (bytes.length == 1) {
				break;
			}

			

			String mc = getLeastCommon(bytes, i);
			String buff = "";
			for (int j = 0; j < bytes.length; j++) {
				if (bytes[j].length() != 0) {
					if (bytes[j].substring(i, i+1).equals(mc)) {
						buff += bytes[j];
						buff += " ";
					}
				}
			}
			bytes = buff.split(" ");
			// System.out.println("AFTER");
			// for (String b : bytes) {
			// 	System.out.println(b + " " + i);
			// }
			// System.out.println("\n");
		}

		int num = 0;
		for (int i = 0; i < length; i++) {
			if (bytes[0].charAt(i) == '1') {
				num += Math.pow(2, length-1-i);
			}
		}
		return num;
	}


  public static void main(String[] args) {
    try {
		File f = new File("input.txt");
		Scanner reader = new Scanner(f);
		String input = "";
		while (reader.hasNextLine()) {
			input += reader.nextLine() + "\n";
		}

		System.out.println("Part one: " + MCBString(input));

		System.out.println("Part two: " + (getOxygen(input) * getCarbon(input)));

		}
		catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
  }
}
