import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Main {

    public static ArrayList<ArrayList<String>> p2Paths = new ArrayList<ArrayList<String>>();

    public static ArrayList<String> getRoutes(ArrayList<ArrayList<String>> input, ArrayList<String> entered, String src) {
        ArrayList<String> out = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            if (input.get(i).get(0).equals(src)) {
                if (!entered.contains(input.get(i).get(1))) {
                    out.add(input.get(i).get(1));
                }
            }
            
        }
        return out;
    }

    public static ArrayList<String> getSmall(ArrayList<ArrayList<String>> input) {
        ArrayList<String> out = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            if (input.get(i).get(0).toLowerCase().equals(input.get(i).get(0)) && !input.get(i).get(0).equals("start")) {
                if (!out.contains(input.get(i).get(0))) {
                    out.add(input.get(i).get(0));
                }
               
            }
            if (input.get(i).get(1).toLowerCase().equals(input.get(i).get(1)) && !input.get(i).get(1).equals("end")) {
                if (!out.contains(input.get(i).get(1))) {
                    out.add(input.get(i).get(1));
                }
            }
        }
        return out;
    }

    public static ArrayList<String> getRoutes2(ArrayList<ArrayList<String>> input, ArrayList<String> entered, ArrayList<Integer> enterCount,
            String src, String candidate) {
        ArrayList<String> out = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            if (input.get(i).get(0).equals(src)) {
                if (!entered.contains(input.get(i).get(1))) {
                    out.add(input.get(i).get(1));
                }
                else if (input.get(i).get(1).equals(candidate) && enterCount.get( entered.indexOf(input.get(i).get(1))) < 2) {
                    out.add(input.get(i).get(1));
                }
            }

        }
        return out;
    }

    public static int partOne(ArrayList<ArrayList<String>> input, ArrayList<String> entered, String src) {
        ArrayList<String> routes = getRoutes(input, entered, src);
        if (routes.size() == 0 && src.equals("end")) {
            return 1;
        }
        int count = 0;
        for (int i = 0; i < routes.size(); i++) {
            ArrayList<String> e = new ArrayList<>(entered);
            if (routes.get(i).toLowerCase().equals(routes.get(i))) {
                e.add(routes.get(i));
            }
            count += partOne(input, e, routes.get(i));
        }
        return count;
    }

    public static int partTwo(ArrayList<ArrayList<String>> input, ArrayList<Integer> enterCount, 
            ArrayList<String> entered, ArrayList<String> path, String candidate, String src) {

        ArrayList<String> routes = getRoutes2(input, entered, enterCount, src, candidate); 
        if (routes.size() == 0 && src.equals("end")) {
            if (!p2Paths.contains(path)) {
                p2Paths.add(path);
                return 1;
            } else {
                return 0;
            }
        }
        int count = 0;
        for (int i = 0; i < routes.size(); i++) {
            ArrayList<String> e = new ArrayList<>(entered);
            ArrayList<Integer> ec = new ArrayList<>(enterCount);
            ArrayList<String> p = new ArrayList<>(path);
            if (routes.get(i).toLowerCase().equals(routes.get(i))) {

                if (!e.contains(routes.get(i))) {
                    e.add(routes.get(i));
                    ec.add(1);
                } else {
                    int idx = e.indexOf(routes.get(i));
                    ec.set(idx, ec.get(idx)+1);
                }
                
            }
            p.add(routes.get(i));
            count += partTwo(input, ec, e, p, candidate, routes.get(i));
        }
        return count;
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
            String[][] data = new String[inp.length][2];
            ArrayList<ArrayList<String>> d = new ArrayList<>();
            ArrayList<String> entered = new ArrayList<>();
            ArrayList<Integer> enterCount = new ArrayList<>();
            int idx = 0;
            for (String i : inp) {
                if (i.split("-")[0].equals("start") || i.split("-")[1].equals("end")) {
                    data[idx][0] = i.split("-")[0];
                    data[idx][1] = i.split("-")[1];
                } else {
                    data[idx][0] = i.split("-")[1];
                    data[idx][1] = i.split("-")[0];
                }
                
                ArrayList<String> inner = new ArrayList<>();
                inner.add(data[idx][0]);
                inner.add(data[idx][1]);
                d.add(inner);
                if (!data[idx][0].equals("start") && !data[idx][1].equals("start")
                && !data[idx][0].equals("end") && !data[idx][1].equals("end")) {
                    inner = new ArrayList<>();
                    inner.add(data[idx][1]);
                    inner.add(data[idx][0]);
                    d.add(inner);
                }
                idx++;
            }

            System.out.println("Part One: " + partOne(d, entered, "start"));

            ArrayList<String> small = getSmall(d);
            int sum = 0;
            for (int i = 0; i < small.size(); i++) {
                sum += partTwo(d, enterCount, entered, new ArrayList<String>(), small.get(i), "start");
            }
            System.out.println("Part Two: " + p2Paths.size());
            

		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
}