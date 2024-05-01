import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Bipartite {

    public static class Edge {
        int src, dest, weight;

        public Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    public static void createGraph(ArrayList<Edge> graph[]) {

        // 0 vertex
        graph[0].add(new Edge(0, 1,0));
        graph[0].add(new Edge(0, 2,0));
        graph[0].add(new Edge(0, 3,0));

        // 1 vertex
        graph[1].add(new Edge(1, 0,0));
        graph[1].add(new Edge(1, 4,0));

        // 2 vertex
        graph[2].add(new Edge(2, 0,0));
        graph[2].add(new Edge(2, 4,0));

        // 3 vertex
        graph[3].add(new Edge(3, 0,0));
        graph[3].add(new Edge(3, 4,0));

        // 4 vertex
        graph[4].add(new Edge(4, 1,0));
        graph[4].add(new Edge(4, 2,0));
        graph[4].add(new Edge(4, 3,0));
    }

    public static void trials(ArrayList<Edge> graph[]) { // retriving the graphs --> O(n^2)(For all the vertex) & O(1)
                                                         // for a single vertex

        System.out.println("Adjacency List : ");
        for (int i = 0; i < graph.length; i++) {
            System.out.println("For vertex : " + i);
            for (int j = 0; j < graph[i].size(); j++) {
                System.out.println("Source      : " + graph[i].get(j).src);
                System.out.println("Destination : " + graph[i].get(j).dest);
                System.out.println("Weight      : " + graph[i].get(j).weight);
            }
            System.out.println();
        }

    }

    /* Problem : Find if the given nodes has path in the graph or not */
    public static boolean hasPath(ArrayList<Edge>[] graph, int src, int dest, boolean vis[]) { // For v nodes and e
        // neighbours of each
        // node --> O(V+E)
        // if my source is my destination
        if (src == dest)
            return true;

        // visiting my current source
        vis[src] = true;

        // asking neighbours for the path using the visited array
        for (int i = 0; i < graph[src].size(); i++) {
            Edge e = graph[src].get(i);
            if (!vis[e.dest] && hasPath(graph, e.dest, dest, vis))
                return true;
        }

        return false;
    }

    public static boolean isBipartite(ArrayList<Edge>[] graph) { // Time complexity --> )(V+E) & Space Complexity -->
        // O(V)
        int[] color = new int[graph.length];

        // initializing my colors with -1(no color)
        for (int i = 0; i < color.length; i++)
            color[i] = -1;

        // BFS traversal for the algorithm
        Queue<Integer> q = new LinkedList<>();

        // for all the graph components
        for (int i = 0; i < graph.length; i++) {
            if (color[i] == -1) { // Only performing BFS when there is no color only
                // Performing BFS
                q.add(i);
                color[i] = 0;
                while (!q.isEmpty()) {
                    int curr = q.remove();
                    for (int j = 0; j < graph[curr].size(); j++) {
                        Edge e = graph[curr].get(j);

                        // Case 1 --> Neighbours having same color
                        if (color[e.dest] == color[curr])
                            return false;

                        // Case 2 --> Neighbours don't have any color
                        if (color[e.dest] == -1) {
                            color[e.dest] = color[curr] == 0 ? 1 : 0; // getting my next color according to curr
                            q.add(e.dest); // adding the colored destination in the queue
                        }
                    }
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        // suppresswarnings is a speacial line which removes all the warnings from the
        // current java file
        // The warning here will be an array of arraylist

        Scanner sc = new Scanner(System.in);
        @SuppressWarnings("unchecked")
        ArrayList<Edge>[] graph = new ArrayList[7];

        // Initialization of graph with a new Arraylist at each index
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        createGraph(graph);
        trials(graph);
        System.out.print("Is Bipartite : ");
        System.out.println(isBipartite(graph));
        // System.out.println(hasPath(graph, 0, 5, new boolean[graph.length]));
    }
}