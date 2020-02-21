import java.util.*;

public class AstarSolver {

    // Array in order to pass it by reference
    private int num_steps;

    public AstarSolver() {
        this.num_steps = 0;
    }

    public Node solve(Node initial) {
        // Reset the num of steps
        num_steps = 0;

        Set<Node> closedSet = new HashSet<>();
        OpenSetList openSet = new OpenSetList();
        Map<Node, Double> gScore = new HashMap<>();

        // Add the initial state to the queue
        openSet.add(initial);
        gScore.put(initial, 0.0);

        while (!openSet.isEmpty()) {
            // Increase the steps
            num_steps++;

            // Remove the first closedSet state from the queue
            Node current = openSet.poll();

            // If s is final, then return it
            if (current.isFinal()) {
                return current;
            }

            closedSet.add(current);

            // Find the states that can be reached from this one
            for (Node n : current.next()) {  //apo olous tous geitones

                if (closedSet.contains(n)) {
                    continue;
                }

                if (!openSet.contains(n)) {
                    openSet.add(n);
                }

                // If current cost to get to that node is bigger than one
                // already calculated, then don't even bother
                double tentative_gScore = gScore.get(current) + current.dist(n.getCoord());
                if (tentative_gScore > gScore.getOrDefault(n, Double.MAX_VALUE)) {
                    continue;
                } else if (tentative_gScore == gScore.getOrDefault(n, Double.MAX_VALUE)) {
                    n.getPrevious().add(current);
                }

                gScore.put(n, tentative_gScore);

                //Fix priority queue if node is in the open set
                if (openSet.contains(n)) {
                    openSet.remove(n);
                    openSet.add(n);
                }

            }
        }
        //if the queue is empty and no sulution was found, return null
        return null;
    }


    public int getStepsNum() {
        return num_steps;
    }
}
