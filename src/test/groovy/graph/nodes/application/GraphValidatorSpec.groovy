package graph.nodes.application

import graph.nodes.application.exception.InvalidGraphException
import graph.nodes.application.exception.NodeNotFoundException
import graph.nodes.domain.model.Edge
import spock.lang.Specification

import static graph.nodes.domain.model.EdgeType.BLUE
import static graph.nodes.domain.model.EdgeType.RED

class GraphValidatorSpec extends Specification {

    GraphValidator graphValidator = new GraphValidator()

    def "should not throw any exceptions for valid graph"() {
        given:
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
        graphValidator.isValid(graph, "0")
        graphValidator.isValid(graph, "1")
        graphValidator.isValid(graph, "5")
        graphValidator.isValid(graph, "6")

        then:
        noExceptionThrown()
    }

    def "should throw exception when start node not in the graph"() {
        given:
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
        graphValidator.isValid(graph, "7")

        then:
        thrown(NodeNotFoundException)
    }

    def "should throw exception if graph is empty"() {
        when:
        graphValidator.isValid([:], "A")

        then:
        thrown(InvalidGraphException)
    }

    def "should throw exception when graph is directed"() {
        given:
        Map<String, List<Edge>> graph = [
                "0": [new Edge("1", BLUE), new Edge("2", RED)],
                "1": [new Edge("0", BLUE), new Edge("2", RED), new Edge("3", RED)],
                "2": [new Edge("0", RED), new Edge("1", RED), new Edge("4", RED)],
                "3": [new Edge("1", RED), new Edge("4", BLUE)],
                "4": [new Edge("3", BLUE), new Edge("5", BLUE)],
                "5": [new Edge("4", BLUE), new Edge("6", BLUE)],
                "6": [new Edge("5", BLUE)]
        ]

        when:
        graphValidator.isValid(graph, "0")

        then:
        thrown(InvalidGraphException)
    }

    def "should throw exception when corresponding edges have different values"() {
        given:
        Map<String, List<Edge>> graph = [
                "0": [new Edge("1", BLUE), new Edge("2", RED)],
                "1": [new Edge("0", BLUE), new Edge("2", RED), new Edge("3", RED)],
                "2": [new Edge("0", RED), new Edge("1", RED), new Edge("4", BLUE)],
                "3": [new Edge("1", RED), new Edge("4", BLUE)],
                "4": [new Edge("2", RED), new Edge("3", BLUE), new Edge("5", BLUE)],
                "5": [new Edge("4", BLUE), new Edge("6", BLUE)],
                "6": [new Edge("5", BLUE)]
        ]

        when:
        graphValidator.isValid(graph, "0")

        then:
        thrown(InvalidGraphException)
    }
}
