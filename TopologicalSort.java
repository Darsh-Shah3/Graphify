import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class TopologicalSort {

    public static class Edge {
        int src, dest;

        public Edge(int src, int dest) {
            this.src = src;
            this.dest = dest;
        }
    }

    public static void createGraph(ArrayList<Edge> graph[]) {
        /*
         * Adjacency List
         * 0 --> {}
         * 1 --> {}
         * 2 --> {2,3}
         * 3 --> {3,1}
         * 4 --> {4,0},{4,1}
         * 5 --> {5,0},{5,2}
         */

        // 2 vertex
        graph[2].add(new Edge(2, 3));

        // 3 vertex
        graph[3].add(new Edge(3, 1));

        // 4 vertex
        graph[4].add(new Edge(4, 0));
        graph[4].add(new Edge(4, 1));

        // 5 vertex
        graph[5].add(new Edge(5, 0));
        graph[5].add(new Edge(5, 2));
    }

    public static void trials(ArrayList<Edge> graph[]) { // retriving the graphs --> O(n^2)(For all the vertex) & O(1)
        // for a single vertex
        System.out.println("Adjacency List : ");
        for (int i = 0; i < graph.length; i++) {
            System.out.println("For vertex : " + i);
            for (int j = 0; j < graph[i].size(); j++) {
                System.out.println("Source      : " + graph[i].get(j).src);
                System.out.println("Destination : " + graph[i].get(j).dest);
            }
            System.out.println();
        }

    }

    public static void calcIndeg(ArrayList<Edge> graph[], int indeg[]) {
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].size(); j++) {
                Edge e = graph[i].get(j);
                indeg[e.dest]++;
            }
        }
    }

    public static void topSort(ArrayList<Edge> graph[]) {
        Queue<Integer> q = new LinkedList<>();
        int indeg[] = new int[graph.length];
        calcIndeg(graph, indeg);

        for (int i = 0; i < indeg.length; i++) {
            if (indeg[i] == 0) {
                q.add(i);
            }
        }
        // bfs
        while (!q.isEmpty()) {
            int curr = q.remove();
            System.out.print(curr + " "); // topological sort

            for (int i = 0; i < graph[curr].size(); i++) {
                Edge e = graph[curr].get(i);
                indeg[e.dest]--;
                if (indeg[e.dest] == 0)
                    q.add(e.dest);
            }
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
        System.out.println("Topological Sort : ");
        topSort(graph);
    }
}