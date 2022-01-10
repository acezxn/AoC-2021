import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Main {
	public static int xyMulti(String input) {
		int x = 0;
		int y = 0;
		String[] commands = input.split("\n");
		for (String c : commands) {

			
			String[] cmd = c.split(" ");
			if (cmd[0].equals("forward")) {
				x += Integer.parseInt(cmd[1]);
			}
			else if (cmd[0].equals("up")) {
				y -= Integer.parseInt(cmd[1]);
			}
			else if (cmd[0].equals("down")) {
				y += Integer.parseInt(cmd[1]);
			}
		}
		return x*y;
	}

	public static int xyMultiWithAim(String input) {
		int x = 0;
		int y = 0;
		int aim = 0;
		
		String[] commands = input.split("\n");
		for (String c : commands) {
			String[] cmd = c.split(" ");
			if (cmd[0].equals("forward")) {
				x += Integer.parseInt(cmd[1]);
				y += aim * Integer.parseInt(cmd[1]);
			}
			else if (cmd[0].equals("up")) {
				aim -= Integer.parseInt(cmd[1]);
			}
			else if (cmd[0].equals("down")) {
				aim += Integer.parseInt(cmd[1]);
			}
		}
		return x*y;
	}

  public static void main(String[] args) {
    try {
		File f = new File("input.txt");
		Scanner reader = new Scanner(f);
		String input = "";
		while (reader.hasNextLine()) {
			input += reader.nextLine() + "\n";
		}
		System.out.println("Part one: " + xyMulti(input));
		System.out.println("Part two: " + xyMultiWithAim(input));
		}
		catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
  }
}
