package graph.nodes.domain.model;

import java.util.List;
import java.util.Map;

public record Graph(Map<String, List<Edge>> adjacencyList) {

    public boolean isEmpty() {
        return adjacencyList.isEmpty();
    }
}
