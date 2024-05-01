import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Dijkstra {
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
         * 0 --> {0,1,2},{0,2,4}
         * 1 --> {1,3,7},{1,2,1}
         * 2 --> {2,4,3}
         * 3 --> {3,5,1}
         * 4 --> {4,3,2},{4,5,5}
         * 5 --> {}
         */

        graph[0].add(new Edge(0, 1, 2));
        graph[0].add(new Edge(0, 2, 4));

        graph[1].add(new Edge(1, 3, 7));
        graph[1].add(new Edge(1, 2, 1));

        graph[2].add(new Edge(2, 4, 3));

        graph[3].add(new Edge(3, 5, 1));

        graph[4].add(new Edge(4, 3, 2));
        graph[4].add(new Edge(4, 5, 5));
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

    static class Pair implements Comparable<Pair> { // comparable inteface work on pair class
        int node;
        int path;

        public Pair(int node, int path) {
            this.node = node;
            this.path = path;
        }

        @Override
        public int compareTo(Pair p2) {
            return this.path - p2.path;// path based sorting for pairs in increasing order
        }
    }

    public static void dijkstra(ArrayList<Edge> graph[], int src) { // O(v+ElogV) where ElogV is sorting time in pq
        int dist[] = new int[graph.length];// dist[i]-> src to i
        for (int i = 0; i < graph.length; i++) {
            if (i != src) {
                dist[i] = Integer.MAX_VALUE;// infinity
            }
        }
        boolean vis[] = new boolean[graph.length];
        PriorityQueue<Pair> pq = new PriorityQueue();
        pq.add(new Pair(src, 0));
        // bfs
        while (!pq.isEmpty()) {
            Pair curr = pq.remove();
            if (!vis[curr.node]) {
                vis[curr.node] = true;
                // neighbour
                for (int i = 0; i < graph[curr.node].size(); i++) {
                    Edge e = graph[curr.node].get(i);
                    int u = e.src;
                    int v = e.dest;
                    int wt = e.weight;

                    if (dist[u] + wt < dist[v]) { // update from src to v
                        dist[v] = dist[u] + wt;
                        pq.add(new Pair(v, dist[v]));
                    }
                }
            }
        }
        // print all source to vertices shortest distance
        for (int i = 0; i < dist.length; i++) {
            System.out.print(dist[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Edge> graph[] = new ArrayList[6];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        createGraph(graph);
        trials(graph);
        System.out.println("Shortest Path List : ");
        dijkstra(graph, 0);
    }
}