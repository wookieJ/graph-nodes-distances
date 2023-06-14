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
        result == ["A": 0, "B": 1, "C": 1, "D": 2] as Map
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
        result == ["A": 0, "B": 1, "C": 4, "D": 3, "E": 2, "F": 1] as Map
    }

    def "should return nodes with distances for valid graph3"() {
        given:
        ShortestDistancesAlgorithm unweightedGraphService = new ShortestDistancesAlgorithm()
        Map<String, List<Edge>> graph = [
                "0": [new Edge("1", BLUE), new Edge("2", RED)],
                "1": [new Edge("0", BLUE), new Edge("3", RED)],
                "2": [new Edge("0", RED), new Edge("4", RED)],
                "3": [new Edge("1", RED), new Edge("4", BLUE)],
                "4": [new Edge("2", RED), new Edge("3", BLUE)],
        ]

        when:
        Map<String, Long> result = unweightedGraphService.shortestDistance(graph, "0")

        then:
        result == ["0": 0, "1": 1, "2": 1, "3": 2, "4": 3] as Map
    }

    def "should return nodes with distances for valid graph4"() {
        given:
        ShortestDistancesAlgorithm unweightedGraphService = new ShortestDistancesAlgorithm()
        Map<String, List<Edge>> graph = [
                "0": [new Edge("1", BLUE), new Edge("2", RED)],
                "1": [new Edge("0", BLUE), new Edge("2", RED), new Edge("3", RED)],
                "2": [new Edge("0", RED), new Edge("1", RED), new Edge("4", RED)],
                "3": [new Edge("1", RED), new Edge("4", BLUE)],
                "4": [new Edge("2", RED), new Edge("3", BLUE), new Edge("5", BLUE)],
                "5": [new Edge("4", BLUE), new Edge("6", BLUE)],
                "6": [new Edge("5", BLUE)]
        ]

        when:
        Map<String, Long> result = unweightedGraphService.shortestDistance(graph, "0")

        then:
        result == ["0": 0, "1": 1, "2": 1, "3": 2, "4": 3, "5": 4, "6": 5] as Map
    }
}
