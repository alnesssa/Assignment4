import java.util.*;

public class WeightedGraph<V> {
    private Set<Vertex<V>> vertices = new HashSet<>();

    public void addVertex(Vertex<V> vertex) {
        vertices.add(vertex);
    }

    public void addEdge(Vertex<V> source, Vertex<V> destination, double weight) {
        source.addAdjacentVertex(destination, weight);
        vertices.add(source);
        vertices.add(destination);
    }

    public Set<Vertex<V>> getVertices() {
        return vertices;
    }
}
