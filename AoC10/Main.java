import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Main {

    public static void partOne(String[] input) {
        long sum_error = 0;
        ArrayList<Long> all_scores = new ArrayList<>();
        for (String line : input) {
            int error = 0;
            long score = 0;
            boolean corrupted = false;
            ArrayList<Character> expected = new ArrayList<>();
            for (int i = 0; i < line.length(); i++) {
                if (
                        line.charAt(i) == '['
                    ||  line.charAt(i) == '{'
                    ||  line.charAt(i) == '('
                    ||  line.charAt(i) == '<'

                    ) {
                        expected.add(line.charAt(i));
                    }
                else if (
                        line.charAt(i) == ']' && expected.get(expected.size()-1) == '['
                    ||  line.charAt(i) == '}' && expected.get(expected.size() - 1) == '{'
                    ||  line.charAt(i) == ')' && expected.get(expected.size() - 1) == '('
                    ||  line.charAt(i) == '>' && expected.get(expected.size() - 1) == '<'
                ) {
                    expected.remove(expected.size()-1);
                } else {
                    corrupted = true;
                    System.out.println("Corrupted");
                    if (line.charAt(i) == ']') {
                        error += 57;
                    }
                    else if (line.charAt(i) == '}') {
                        error += 1197;
                    }
                    else if (line.charAt(i) == ')') {
                        error += 3;
                    }
                    else if (line.charAt(i) == '>') {
                        error += 25137;
                    }
                    break;
                }
                
        }
        if (!corrupted) {
            for (int j = expected.size()-1; j >= 0 ; j--) {
                if (expected.get(j) == '(') {
                    score *= 5;
                    score += 1;
                } else if (expected.get(j) == '[') {
                    score *= 5;
                    score += 2;
                } else if (expected.get(j) == '{') {
                    score *= 5;
                    score += 3;
                } else if (expected.get(j) == '<') {
                    score *= 5;
                    score += 4;
                }
            }
            all_scores.add(score);
        }
        sum_error += error;
    }
    
        Collections.sort(all_scores);
        System.out.println("PartOne: " + sum_error);
        System.out.println("PartTwo: " + all_scores.get(all_scores.size()/2));
        
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
            partOne(inp);
            // System.out.println(partOne(data));
			// System.out.println(partOne(inp[0], 14));

		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
}
