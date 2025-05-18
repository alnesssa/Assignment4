import java.util.*;

public class DijkstraSearch<V> implements Search<V> {
    private Map<Vertex<V>, Double> distTo = new HashMap<>();
    private Map<Vertex<V>, Vertex<V>> edgeTo = new HashMap<>();
    private PriorityQueue<Vertex<V>> priorityQueue;
    private Vertex<V> start;

    public DijkstraSearch(WeightedGraph<V> graph, Vertex<V> start) {
        this.start = start;
        for (Vertex<V> v : graph.getVertices()) {
            distTo.put(v, Double.POSITIVE_INFINITY);
        }
        distTo.put(start, 0.0);

        Comparator<Vertex<V>> comparator = Comparator.comparing(distTo::get);
        priorityQueue = new PriorityQueue<>(comparator);
        priorityQueue.add(start);

        while (!priorityQueue.isEmpty()) {
            Vertex<V> current = priorityQueue.poll();
            for (Map.Entry<Vertex<V>, Double> entry : current.getAdjacentVertices().entrySet()) {
                relax(current, entry.getKey(), entry.getValue());
            }
        }
    }

    private void relax(Vertex<V> from, Vertex<V> to, double weight) {
        double newDist = distTo.get(from) + weight;
        if (newDist < distTo.get(to)) {
            distTo.put(to, newDist);
            edgeTo.put(to, from);
            priorityQueue.remove(to);
            priorityQueue.add(to);
        }
    }

    @Override
    public boolean hasPathTo(Vertex<V> destination) {
        return distTo.get(destination) < Double.POSITIVE_INFINITY;
    }

    @Override
    public List<Vertex<V>> pathTo(Vertex<V> destination) {
        if (!hasPathTo(destination)) return null;
        LinkedList<Vertex<V>> path = new LinkedList<>();
        for (Vertex<V> at = destination; at != start; at = edgeTo.get(at)) {
            path.addFirst(at);
        }
        path.addFirst(start);
        return path;
    }
}
