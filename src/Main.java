public class Main {
    public static void main(String[] args) {
        WeightedGraph<String> graph = new WeightedGraph<>();

        Vertex<String> a = new Vertex<>("A");
        Vertex<String> b = new Vertex<>("B");
        Vertex<String> c = new Vertex<>("C");
        Vertex<String> d = new Vertex<>("D");

        graph.addEdge(a, b, 1);
        graph.addEdge(a, c, 4);
        graph.addEdge(b, c, 2);
        graph.addEdge(c, d, 1);

        System.out.println("BFS from A:");
        Search<String> bfs = new BreadthFirstSearch<>(graph, a);
        System.out.println(bfs.pathTo(d));

        System.out.println("\nDijkstra from A:");
        Search<String> dijkstra = new DijkstraSearch<>(graph, a);
        System.out.println(dijkstra.pathTo(d));
    }
}
