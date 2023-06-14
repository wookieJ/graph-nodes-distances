package io.mend.interview.application

import io.mend.interview.domain.model.Edge
import io.mend.interview.domain.model.NodeResponse
import spock.lang.Specification

import static io.mend.interview.domain.model.EdgeType.BLUE
import static io.mend.interview.domain.model.EdgeType.RED

class UnweightedGraphServiceSpec extends Specification {
    def "should return nodes with distances for valid graph"() {
        given:
        UnweightedGraphService unweightedGraphService = new UnweightedGraphService()
        Map<String, List<Edge>> graph = [
                "A": [new Edge("B", RED), new Edge("C", BLUE)],
                "B": [new Edge("A", RED), new Edge("D", BLUE)],
                "C": [new Edge("A", BLUE), new Edge("D", RED)],
                "D": [new Edge("B", BLUE), new Edge("C", RED)]
        ]

        when:
        List<NodeResponse> result = unweightedGraphService.getNodesWithDistances(graph, "A")

        then:
        result.size() == 4
        result.collect{it.distance}.toList() == [0, 1, 1, 2]
    }
    def "should return nodes with distances for valid graph2"() {
        given:
        UnweightedGraphService unweightedGraphService = new UnweightedGraphService()
        Map<String, List<Edge>> graph = [
                "A": [new Edge("B", RED), new Edge("F", BLUE)],
                "B": [new Edge("A", RED), new Edge("C", RED)],
                "C": [new Edge("B", RED), new Edge("D", BLUE)],
                "D": [new Edge("C", BLUE), new Edge("E", BLUE)],
                "E": [new Edge("D", BLUE), new Edge("F", BLUE)],
                "F": [new Edge("E", BLUE), new Edge("A", BLUE)]
        ]

        when:
        List<NodeResponse> result = unweightedGraphService.getNodesWithDistances(graph, "A")

        then:
        result.size() == 6
        result.collect{it.distance}.toList() == [0, 1, 4, 3, 2, 1]
    }

    def "should return empty list for empty graph"() {
        given:
        UnweightedGraphService unweightedGraphService = new UnweightedGraphService()

        when:
        List<NodeResponse> result = unweightedGraphService.getNodesWithDistances([:], "")

        then:
        result.isEmpty()
    }
}
