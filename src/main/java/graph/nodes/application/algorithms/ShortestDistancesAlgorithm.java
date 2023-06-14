package graph.nodes.application.algorithms;

import graph.nodes.domain.model.Distance;
import graph.nodes.domain.model.Edge;
import graph.nodes.domain.model.EdgeType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

@Component
public class ShortestDistancesAlgorithm {

    public Map<String, Long> shortestDistance(Map<String, List<Edge>> adjacencyList, String rootNode) {
        var cache = new HashMap<String, Distance>();
        cache.put(rootNode, new Distance(0L, 0L)); // rootNode has 0 distance to itself

        // neighbours of rootNode have distance of 1
        adjacencyList.get(rootNode).forEach(neighbor -> {
            var redCnt = neighbor.getType() == EdgeType.RED ? 1L : 0L;
            cache.put(neighbor.getDestNode(), new Distance(1L, redCnt));
        });

        var distances = new HashMap<String, Long>();
        for (String node : adjacencyList.keySet()) {
            var visited = new HashSet<String>();
            Distance result = calcDistanceToRoot(node, adjacencyList, node, cache, visited);
            distances.put(node, result.distance());
        }
        return distances;
    }

    private Distance calcDistanceToRoot(String node, Map<String, List<Edge>> adjacencyList, String startNode, HashMap<String, Distance> cache, HashSet<String> visited) {
        if (cache.containsKey(node)) {
            return cache.get(node);
        }
        visited.add(node);

        // calculate the shortest distance which is min distance of each neighbours distance plus distance to neighbor. Consider edge type here
        var shortestNeighboursDistance = adjacencyList.get(node).stream()
                .filter(canVisitNeighbor(startNode, visited))
                .map(neighbor -> {
                    var neighborDistance = calcDistanceToRoot(neighbor.getDestNode(), adjacencyList, startNode, cache, visited);
                    long redEdgesToNeighbor = neighbor.getType() == EdgeType.RED ? 1 : 0;
                    return new Distance(neighborDistance.distance() + 1, neighborDistance.redEdgesNumber() + redEdgesToNeighbor);
                })
                .min(Distance::compareTo);
        if (shortestNeighboursDistance.isPresent()) {
            var result = shortestNeighboursDistance.get();
            if (node.equals(startNode)) {
                cache.put(node, result);
            }
            return result;
        } else {
            return new Distance(Long.MAX_VALUE, 2L);
        }
    }

    private static Predicate<Edge> canVisitNeighbor(String startNode, HashSet<String> visited) {
        return neighbor -> !neighbor.getDestNode().equals(startNode) && !visited.contains(neighbor.getDestNode());
    }
}
