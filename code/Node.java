import java.util.ArrayList;
import java.util.*;

public class Node {

    private Point coord;
    private List<Node> previous;
    private double distFromSource;
    private Point end_p;

    private Map<Point, List<Point>> adjMap;


    public void printSelf() {
        System.out.println(coord.X + ", " + coord.Y);
    }

    public double getDist() {
        return distFromSource;
    }

    public double getOverallDist() {
        return distFromSource + dist(end_p);
    }

    public Point getPoint() {
        return this.coord;
    }

    public List<Node> getPrevious() {
        return previous;
    }

    public Point getCoord() {
        return coord;
    }

    public Node(Point p, List<Node> previous, double distFromSource, Map<Point, List<Point>> adjMap, Point end_p) {

        this.coord = p;
        this.end_p = end_p;
        this.previous = previous;
        this.distFromSource = distFromSource;

        this.adjMap = adjMap;
    }

    public boolean isFinal() {
        return coord.equals(end_p);
    }

    public Collection<Node> next() {
        Collection<Node> nodes = new ArrayList<>();

        //All the possible moves
        for (Point p : adjMap.get(coord)) {
            List<Node> temp = new ArrayList<Node>();
            temp.add(this);
            nodes.add(new Node(p, temp, distFromSource + dist(p), adjMap, end_p));
        }

        return nodes;
    }

    public double dist(Point p1) {
        return Math.sqrt(Math.pow(p1.X - coord.X, 2) + Math.pow(p1.Y - coord.Y, 2));
    }

    @Override
    public int hashCode() {
        return coord.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node that = (Node) o;

        return coord.equals(that.coord);
    }

}

