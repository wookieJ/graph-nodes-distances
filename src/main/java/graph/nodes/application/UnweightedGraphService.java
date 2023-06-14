package graph.nodes.application;

import graph.nodes.application.algorithms.ShortestDistancesAlgorithm;
import graph.nodes.domain.model.Graph;
import graph.nodes.infrastructure.rest.model.NodeResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnweightedGraphService {

    private final ShortestDistancesAlgorithm shortestDistancesAlgorithm;

    public UnweightedGraphService(ShortestDistancesAlgorithm shortestDistancesAlgorithm) {
        this.shortestDistancesAlgorithm = shortestDistancesAlgorithm;
    }

    public List<NodeResponse> getNodesWithDistances(Graph graph, String startNode) {
        if (graph.isEmpty() || startNode.isEmpty()) {
            return List.of();
        }
        var distances = shortestDistancesAlgorithm.shortestDistance(graph.adjacencyList(), startNode);
        return distances.entrySet()
                .stream()
                .map(entry -> new NodeResponse(entry.getKey(), entry.getValue()))
                .toList();
    }
}
