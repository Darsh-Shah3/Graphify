import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BFS {

    public static class Edge {
        int src, dest, weight;

        public Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    public static void createGraph(ArrayList<Edge> graph[]) {
        /*
         * Adjacency List
         * 0 --> {0,1,0},{0,2,0}
         * 1 --> {1,3,0},{1,0,0}
         * 2 --> {2,4,0},{2,0,0}
         * 3 --> {3,4,0},{3,5,0},{3,2,0}
         * 4 --> {4,2,0},{4,3,0},{4,5,0}
         * 5 --> {5,3,0},{5,4,0},{5,6,0}
         * 6 --> {6,5,0}
         * BFS --> 0 1 2 3 4 5 6
         */

        // 0 vertex
        graph[0].add(new Edge(0, 1, 0));
        graph[0].add(new Edge(0, 2, 0));

        // 1 vertex
        graph[1].add(new Edge(1, 3, 0));
        graph[1].add(new Edge(1, 0, 0));

        // 2 vertex
        graph[2].add(new Edge(2, 0, 0));
        graph[2].add(new Edge(2, 4, 0));

        // 3 vertex
        graph[3].add(new Edge(3, 2, 0));
        graph[3].add(new Edge(3, 4, 0));
        graph[3].add(new Edge(3, 5, 0));

        // 4 vertex
        graph[4].add(new Edge(4, 2, 0));
        graph[4].add(new Edge(4, 3, 0));
        graph[4].add(new Edge(4, 5, 0));

        // 5 vertex
        graph[5].add(new Edge(5, 3, 0));
        graph[5].add(new Edge(5, 4, 0));
        graph[5].add(new Edge(5, 6, 0));

        // 6 vertex
        graph[6].add(new Edge(6, 5, 0));

    }

    public static void trials(ArrayList<Edge> graph[]) { // retriving the graphs --> O(n^2)(For all the vertex) & O(1) for a single vertex

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

    /* Breadth First Search! */
    public static void BFS(ArrayList<Edge> graph[]) { // For v nodes and e neighbours of each node --> O(V+E)
        Queue<Integer> q = new LinkedList<>();
        boolean vis[] = new boolean[graph.length];
        q.add(0);// source=0

        while (!q.isEmpty()) {
            // removing curr element
            int curr = q.remove();

            // checking if the curr element is visited or not
            if (!vis[curr]) {
                System.out.print(curr + " ");
                vis[curr] = true; // if not then making it print and visit it

                // adding all the neighbours of particular vertices into queue
                for (int i = 0; i < graph[curr].size(); i++) {
                    Edge e = graph[curr].get(i);
                    q.add(e.dest);
                }
            }
        }
        System.out.println();
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
        System.out.println("Breadth First Search : ");
        BFS(graph);
        System.out.println(hasPath(graph, 0, 5, new boolean[graph.length]));
    }
}