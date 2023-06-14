package graph.nodes.infrastructure.rest;

import graph.nodes.application.GraphValidator;
import graph.nodes.application.exception.InvalidGraphException;
import graph.nodes.application.UnweightedGraphService;
import graph.nodes.infrastructure.rest.model.GraphRequest;
import graph.nodes.infrastructure.rest.model.NodesListResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GraphController {

    private final UnweightedGraphService unweightedGraphService;

    private final GraphValidator graphValidator;

    public GraphController(UnweightedGraphService unweightedGraphService, GraphValidator graphValidator) {
        this.unweightedGraphService = unweightedGraphService;
        this.graphValidator = graphValidator;
    }

    @PostMapping(value = "/graphs/nodes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NodesListResponse> getNodes(@RequestBody GraphRequest graphRequest) throws RuntimeException {
        System.out.println(graphRequest);
        graphValidator.isValid(graphRequest.getGraph(), graphRequest.getStartNode());
        var nodesDistances = unweightedGraphService.getNodesWithDistances(graphRequest.toDomain(), graphRequest.getStartNode());
        var nodesResponse = new NodesListResponse(nodesDistances);
        return ResponseEntity.ok(nodesResponse);
    }
}
