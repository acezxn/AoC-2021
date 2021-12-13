import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Main {

    public static int flashes = 0;

    public static int[][] flash(int[][] pool, int i, int j) {
        // check left and up
        if (j > 0 && i > 0) {
            if (pool[i - 1][j - 1] != 0) {
                pool[i - 1][j - 1]++;
                if (pool[i - 1][j - 1] > 9) {
                    pool[i - 1][j - 1] = 0;
                    pool = flash(pool, i-1, j-1);
                }
            }
        }
        // check left and down
        if (j > 0 && i < pool.length-1 ) {
            if (pool[i + 1][j - 1] != 0) {
                pool[i + 1][j - 1]++;
                if (pool[i + 1][j - 1] > 9) {
                    pool[i + 1][j - 1] = 0;
                    pool = flash(pool, i + 1, j - 1);
                }
            }
        }
        
        // check left
        if (j > 0) {
            if (pool[i][j - 1] != 0) {
                pool[i][j - 1]++;
                if (pool[i][j - 1] > 9) {
                    pool[i][j - 1] = 0;
                    pool = flash(pool, i, j - 1);
                }
            }
        }
        // check right and up
        if (j < pool[0].length - 1 && i > 0) {
            if (pool[i - 1][j + 1] != 0) {
                pool[i - 1][j + 1]++;
                if (pool[i - 1][j + 1] > 9) {
                    pool[i - 1][j + 1] = 0;
                    pool = flash(pool, i - 1, j + 1);
                }
            }
        }
        // check right and down
        if (j < pool[0].length - 1 && i < pool.length - 1) {
            if (pool[i + 1][j + 1] != 0) {
                pool[i + 1][j + 1]++;
                if (pool[i + 1][j + 1] > 9) {
                    pool[i + 1][j + 1] = 0;
                    pool = flash(pool, i + 1, j + 1);
                }
            }
        }
        // check right
        if (j < pool[0].length - 1) {
            if (pool[i][j + 1] != 0) {
                pool[i][j + 1]++;
                if (pool[i][j + 1] > 9) {
                    pool[i][j + 1] = 0;
                    pool = flash(pool, i, j + 1);
                }
            }
        }
        // check up
        if (i > 0) {
            if (pool[i - 1][j] != 0) {
                pool[i - 1][j]++;
                if (pool[i - 1][j] > 9) {
                    pool[i - 1][j] = 0;
                    pool = flash(pool, i - 1, j);
                }
            }
        }
        // check down
        if (i < pool.length-1) {
            if (pool[i + 1][j] != 0) {
                pool[i + 1][j]++;
                if (pool[i + 1][j] > 9) {
                    pool[i + 1][j] = 0;
                    pool = flash(pool, i + 1, j);
                }
            }
        }
        return pool;
    }

    public static void partOne(int[][] pool) {
        int steps = 1000;
        int firstSync = -1;
        boolean synced = false;
        for (int z = 0; z < steps; z++) {
            for (int i = 0; i < pool.length; i++) { // vertical
                for (int j = 0; j < pool[0].length; j++) { // horizontal
                    pool[i][j] += 1;
                }
            }

            for (int i = 0; i < pool.length; i++) { // vertical
                for (int j = 0; j < pool[0].length; j++) { // horizontal
                    if (pool[i][j] > 9) {
                        pool[i][j] = 0;
                        pool = flash(pool, i, j);
                    }
                    
                }
            }
            System.out.println(flashes);
            int f = 0;
            for (int i = 0; i < pool.length; i++) { // vertical
                for (int j = 0; j < pool[0].length; j++) { // horizontal
                    System.out.print(pool[i][j] + " ");
                    if (pool[i][j] == 0) {
                        f++;
                    }
                }
                System.out.println();
            }
            if (f == (pool.length * pool[0].length) && !synced) {
                firstSync = z+1;
                synced = true;
            }
            System.out.println();
            if (z < 100) {
                flashes += f;
            }
            
            
        }
        
        System.out.println("PartOne: " + flashes);
        System.out.println("PartTwo: " + firstSync);
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
                }
            }
            partOne(data);

		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
}