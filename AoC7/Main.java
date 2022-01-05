import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Main {


	public static int partOne(String input) {
		ArrayList<Integer> pool = new ArrayList<Integer>();
		for (String i : input.split(",")) {
			pool.add(Integer.parseInt(i));
		}
		
		int max = Collections.max(pool);
		int min = Collections.min(pool);
		long errorMin = Long.MAX_VALUE;
		int position = min;
		for (int i = min; i <= max; i++) {
			long error = 0;
			for (String s : input.split(",")) {
				error += Math.abs(i - Integer.parseInt(s));

			}
			if (error < errorMin) {
				errorMin = error;
				position = i;
			}
		}
		return position;

	}

	public static int partTwo(String input) {
		ArrayList<Integer> pool = new ArrayList<Integer>();
		for (String i : input.split(",")) {
			pool.add(Integer.parseInt(i));
		}
		
		int max = Collections.max(pool);
		int min = Collections.min(pool);
		long errorMin = Long.MAX_VALUE;
		int position = min;
		for (int i = min; i <= max; i++) {
			long error = 0;
			for (String s : input.split(",")) {
				int n = Math.abs(i - Integer.parseInt(s));
				error += Math.abs(n * (1+n) / 2);

			}
			if (error < errorMin) {
				errorMin = error;
				position = i;
			}
		}
		return position;

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
			System.out.println("Part one: " + partOne(inp[0]));
			System.out.println("Part two: " + partTwo(inp[0]));
			

		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
}
