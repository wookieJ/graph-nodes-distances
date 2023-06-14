package io.mend.interview.application;

import io.mend.interview.domain.model.Edge;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class GraphValidator {

    public void isValid(Map<String, List<Edge>> graph, String startNode) throws InvalidGraphException {
        if (!graph.containsKey(startNode)) {
            throw new InvalidGraphException("NO_STARTING_NODE");
        }
        // todo - check if graph is undirected: for every A->B must exist edge B->A
        // todo - check if corresponding edges have equal value (both RED or both BLUE)
        // todo - add handler and return Error Response with 4xx response code
    }
}
