package io.mend.interview.application;

import io.mend.interview.domain.model.Edge;
import io.mend.interview.domain.model.EdgeType;
import io.mend.interview.domain.model.NodeResponse;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UnweightedGraphService {
    public List<NodeResponse> getNodesWithDistances(Map<String, List<Edge>> graph, String startNode) {
        if (graph.isEmpty() || startNode.isEmpty()) {
            return List.of();
        }
        var distances = shortestDistance(graph, startNode);
        return distances.entrySet()
                .stream()
                .map(entry -> new NodeResponse(entry.getKey(), entry.getValue()))
                .toList();
    }

    public static Map<String, Long> shortestDistance(Map<String, List<Edge>> adjacencyList, String startNode) {
        Map<String, Long> distances = new HashMap<>();
        distances.put(startNode, 0L);

        Stack<String> stack = new Stack<>();
        stack.push(startNode);

        while (!stack.isEmpty()) {
            var currentNode = stack.pop();
            var currentDistance = distances.get(currentNode);
            var redOccuranceNumber = 0;

            for (Edge edge : adjacencyList.get(currentNode)) {
                if (!distances.containsKey(edge.getDestNode())) {
                    stack.push(edge.getDestNode());
                    long newDistance = currentDistance + 1;
                    if (edge.getType() == EdgeType.RED) {
                        redOccuranceNumber++;
                    }
                    if (redOccuranceNumber <= 1) {
                        distances.put(edge.getDestNode(), newDistance);
                    }
                }
            }
        }

        return distances;
    }
}
