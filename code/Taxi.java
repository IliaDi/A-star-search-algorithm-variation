import java.io.*;
import java.util.*;

public class Taxi {

    // java Taxi nodes.csv taxis.csv client.csv
    public static void main(String[] args) {

        // Arg[1] = Search_width
        String files_directory = args[0];
        if (!files_directory.substring(files_directory.length() - 1).equals("/")) {
            files_directory += "/";
        }

        String taxis_file = files_directory + "taxis.csv";
        String client_file = files_directory + "client.csv";
        String nodes_file = files_directory + "nodes.csv";

        AstarSolver solver = new AstarSolver();
        InfoExcavator info = new InfoExcavator();
        Point client = info.getPointFromLine(client_file, 1);
        Map<Point, List<Point>> adjMap = info.makeAdjMap(nodes_file);

        for (int i = 1; i < info.getTaxisNum(taxis_file) + 1; i++) {
            //Redirect the output to fhe files
            try {
                PrintStream out = new PrintStream(new FileOutputStream(files_directory + "taxi" + i + ".out"));
                System.setOut(out);
            } catch (FileNotFoundException e) {
                System.out.println("Cant create the output file");
                return;
            }

            Point taxi = info.getPointFromLine(taxis_file, i);

            adjMap = info.addPointToMap(adjMap, taxi);
            adjMap = info.addPointToMap(adjMap, client);

            List<Node> empty = new ArrayList<>();
            empty.add(null);
            Node result = solver.solve(new Node(taxi, empty, 0, adjMap, client));
            writePath(result);
            writeStepsNum(files_directory + "taxi" + i + "_", solver.getStepsNum());
        }


    }

    private static void writePath(Node node) {
        if (node.getPrevious().get(0) != null) {
            for (Node i : node.getPrevious()) {
                if (i != null) {
                    i.printSelf();
                    writePath(i);
                } else {
                    continue;
                }
            }
        }
        else{node.printSelf();}
    }


    public static void writeStepsNum(String files_directory, int steps_num) {
        try {
            PrintStream out = new PrintStream(new FileOutputStream(files_directory + "steps_num"));
            System.setOut(out);
            System.out.println(steps_num);
        } catch (FileNotFoundException e) {
            System.out.println("Cant create the output file");
            return;
        }
    }

}


