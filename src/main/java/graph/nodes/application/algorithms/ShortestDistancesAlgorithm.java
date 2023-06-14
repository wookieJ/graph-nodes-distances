package graph.nodes.application.algorithms;

import graph.nodes.domain.model.Edge;
import graph.nodes.domain.model.EdgeType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

@Component
public class ShortestDistancesAlgorithm {
    public Map<String, Long> shortestDistance(Map<String, List<Edge>> adjacencyList, String startNode) {
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
