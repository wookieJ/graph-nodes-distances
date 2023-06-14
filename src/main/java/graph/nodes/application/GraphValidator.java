package graph.nodes.application;

import graph.nodes.application.exception.InvalidGraphException;
import graph.nodes.application.exception.NodeNotFoundException;
import graph.nodes.domain.model.Edge;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class GraphValidator {

    public void isValid(Map<String, List<Edge>> graph, String startNode) throws RuntimeException {
        if (graph.isEmpty()) {
            throw new InvalidGraphException("Empty graph");
        }
        if (!graph.containsKey(startNode)) {
            throw new NodeNotFoundException("Cannot find start node in graph");
        }
        var graphEdgesValidationErrorMessage = validateGraphEdges(graph);
        if (graphEdgesValidationErrorMessage != null) {
            throw new InvalidGraphException(graphEdgesValidationErrorMessage);
        }
    }

    private String validateGraphEdges(Map<String, List<Edge>> graph) {
        for (Map.Entry<String, List<Edge>> entry : graph.entrySet()) {
            for (Edge edge : entry.getValue()) {
                if (!graph.containsKey(edge.getDestNode())) {
                    return "graph should be undirected";
                }
                Optional<Edge> correspondingEdge = containsEdge(graph, edge, entry.getKey());
                if (correspondingEdge.isEmpty()) {
                    return "graph should be undirected";
                }
                if (correspondingEdge.get().getType() != edge.getType()) {
                    return "corresponding edges should have equal values";
                }
            }
        }
        return null;
    }

    private static Optional<Edge> containsEdge(Map<String, List<Edge>> graph, Edge edge, String entryKey) {
        return graph.get(edge.getDestNode()).stream().filter(it -> it.getDestNode().equals(entryKey)).findFirst();
    }
}
