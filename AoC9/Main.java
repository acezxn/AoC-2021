import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Main {


	public static long partOne(int[][] input) {
        long sum = 0;
        for (int i = 0; i < input.length; i++) { // vertical
            for (int j = 0; j < input[0].length; j++) { // horizontal
                boolean isLowest = true;
                int currentPt = input[i][j];
                // if j > 0 --> check left
                if (j > 0) {
                    if (input[i][j-1] <= currentPt) {
                        isLowest = false;
                    }
                }
                // if j < input[0].length -1 --> check right
                if (j < input[0].length -1) {
                    if (input[i][j + 1] <= currentPt) {
                        isLowest = false;
                    }
                }
                // if i > 0 --> check up
                if (i > 0) {
                    if (input[i-1][j] <= currentPt) {
                        isLowest = false;
                    }
                }
                // if i < input.length -1 --> check down
                if (i < input.length-1) {
                    if (input[i+1][j] <= currentPt) {
                        isLowest = false;
                    }
                }

                if (isLowest) {
                    // System.out.println(currentPt);
                    sum += 1 + currentPt;
                }

            }
        }
        return sum;
	}

    public static long partTwo(int[][] input) {
        ArrayList<ArrayList<Integer>> lp = new ArrayList<>();
        int[][] entered = new int[input.length][input[0].length];
        for (int i = 0; i < input.length; i++) { // vertical
            for (int j = 0; j < input[0].length; j++) { // horizontal
                entered[i][j] = 0;
                boolean isLowest = true;
                int currentPt = input[i][j];
                // if j > 0 --> check left
                if (j > 0) {
                    if (input[i][j - 1] <= currentPt) {
                        isLowest = false;
                    }
                }
                // if j < input[0].length -1 --> check right
                if (j < input[0].length - 1) {
                    if (input[i][j + 1] <= currentPt) {
                        isLowest = false;
                    }
                }
                // if i > 0 --> check up
                if (i > 0) {
                    if (input[i - 1][j] <= currentPt) {
                        isLowest = false;
                    }
                }
                // if i < input.length -1 --> check down
                if (i < input.length - 1) {
                    if (input[i + 1][j] <= currentPt) {
                        isLowest = false;
                    }
                }

                if (isLowest) {
                    // System.out.println(currentPt);
                    ArrayList<Integer> coord = new ArrayList<Integer>();
                    coord.add(j);
                    coord.add(i);
                    lp.add(coord);

                }

            }
        }
        
        ArrayList<Long> areas = new ArrayList<>();
        for (int i = 0; i < lp.size(); i++) {
            long area = area(lp.get(i).get(0), lp.get(i).get(1), input[0].length, input.length, input, entered);
            // System.out.println(area);
            areas.add(area);
        }
        long product = 1;
        for (int i = 0; i < 3; i++) {
            long max = Collections.max(areas);
            product *= max;
            areas.remove(areas.indexOf(max));
        }
        return product;
        
    }

    public static long area(int x, int y, int rightbound, int downbound, int[][] input, int[][] entered) {
        if (input[y][x] == 9) {
            return 0;
        }
        entered[y][x] = 1;
        long a = 1;
        if (x > 0 && entered[y][x-1] == 0) { // check left
            a += area(x-1, y, rightbound, downbound, input, entered);
        }
        if (x < rightbound-1 && entered[y][x+1] == 0) { // check right
            a += area(x+1, y, rightbound, downbound, input, entered);
        }
        if (y > 0 && entered[y-1][x] == 0) { // check up
            a += area(x, y-1, rightbound, downbound, input, entered);
        }
        if (y < downbound-1 && entered[y+1][x] == 0) { // check down
            a += area(x, y+1, rightbound, downbound, input, entered);
        }
        return a;
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
            int[][] data = new int[inp.length][inp[0].length()];
            for (int i = 0; i < inp.length; i++) {
                for (int j = 0; j < inp[0].length(); j++) {
                    data[i][j] = Integer.parseInt(inp[i].substring(j, j+1));
                    // System.out.println(data[i][j]);
                }
            }
            System.out.println("PartOne: " + partOne(data));
            System.out.println("PartTwo: " + partTwo(data));
			// System.out.println(partOne(inp[0], 14));

		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
}
