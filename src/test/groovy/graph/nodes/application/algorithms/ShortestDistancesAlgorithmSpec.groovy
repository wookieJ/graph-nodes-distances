package graph.nodes.application.algorithms

import graph.nodes.domain.model.Edge
import spock.lang.Specification

import static graph.nodes.domain.model.EdgeType.BLUE
import static graph.nodes.domain.model.EdgeType.RED

class ShortestDistancesAlgorithmSpec extends Specification {
    def "should return nodes with distances for valid graph"() {
        given:
        ShortestDistancesAlgorithm shortestDistancesAlgorithm = new ShortestDistancesAlgorithm()
        Map<String, List<Edge>> graph = [
                "A": [new Edge("B", RED), new Edge("C", BLUE)],
                "B": [new Edge("A", RED), new Edge("D", BLUE)],
                "C": [new Edge("A", BLUE), new Edge("D", RED)],
                "D": [new Edge("B", BLUE), new Edge("C", RED)]
        ]

        when:
        Map<String, Long> result = shortestDistancesAlgorithm.shortestDistance(graph, "A")

        then:
        result == ["A": 0, "B": 1, "C": 1, "D": 2]
    }

    def "should return nodes with distances for valid graph2"() {
        given:
        ShortestDistancesAlgorithm shortestDistancesAlgorithm = new ShortestDistancesAlgorithm()
        Map<String, List<Edge>> graph = [
                "A": [new Edge("B", RED), new Edge("F", BLUE)],
                "B": [new Edge("A", RED), new Edge("C", RED)],
                "C": [new Edge("B", RED), new Edge("D", BLUE)],
                "D": [new Edge("C", BLUE), new Edge("E", BLUE)],
                "E": [new Edge("D", BLUE), new Edge("F", BLUE)],
                "F": [new Edge("E", BLUE), new Edge("A", BLUE)]
        ]

        when:
        Map<String, Long> result = shortestDistancesAlgorithm.shortestDistance(graph, "A")

        then:
        result == ["A": 0, "B": 1, "C": 4, "D": 3, "E": 2, "F": 1]
    }
}
