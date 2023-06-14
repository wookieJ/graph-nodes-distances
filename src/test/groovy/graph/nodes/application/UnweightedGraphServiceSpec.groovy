package graph.nodes.application

import graph.nodes.application.algorithms.ShortestDistancesAlgorithm
import graph.nodes.domain.model.Graph
import graph.nodes.infrastructure.rest.model.NodeResponse
import spock.lang.Specification

class UnweightedGraphServiceSpec extends Specification {

    ShortestDistancesAlgorithm shortestDistancesAlgorithm = Mock()
    UnweightedGraphService unweightedGraphService = new UnweightedGraphService(shortestDistancesAlgorithm)

    def "should return empty list for empty graph"() {
        when:
        List<NodeResponse> result = unweightedGraphService.getNodesWithDistances(new Graph([:]), "")

        then:
        result.isEmpty()
    }
}
