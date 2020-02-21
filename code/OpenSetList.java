import java.util.*;

// PriorityQueue's method contains has O(n) complexity
// In order to speed this up to O(1) we also use a Set to keep track of 
// objects inside the PriorityQueue
public class OpenSetList {
    private PriorityQueue<Node> nodes_list;
    private Set<Node> nodes_in_openSet;
    private PriorityQueue<Node> inverse_list;

    public OpenSetList() {
        this.nodes_list = new PriorityQueue<Node>(new NodeDistComparator());
        this.inverse_list = new PriorityQueue<Node>(new NodeDistInverseComparator());
        this.nodes_in_openSet = new HashSet<>();
    }

    public void add(Node n) {
        int add = 1;

        nodes_list.add(n);
        nodes_in_openSet.add(n);
        inverse_list.add(n);

    }

    public boolean isEmpty() {
        return nodes_list.isEmpty();
    }

    public Node poll() {
        Node n = nodes_list.poll();
        inverse_list.remove(n);
        nodes_in_openSet.remove(n);
        return n;
    }

    public boolean contains(Node n) {
        return nodes_in_openSet.contains(n);
    }

    public void remove(Node n) {
        nodes_list.remove(n);
        nodes_in_openSet.remove(n);
        inverse_list.remove(n);
    }
}
