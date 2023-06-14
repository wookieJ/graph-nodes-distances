package io.mend.interview.infrastructure.rest;

import io.mend.interview.application.GraphValidator;
import io.mend.interview.application.InvalidGraphException;
import io.mend.interview.application.UnweightedGraphService;
import io.mend.interview.domain.model.GraphRequest;
import io.mend.interview.domain.model.NodesListResponse;
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
    public ResponseEntity<NodesListResponse> getNodes(@RequestBody GraphRequest graphRequest) throws InvalidGraphException {
        System.out.println(graphRequest);
        graphValidator.isValid(graphRequest.getGraph(), graphRequest.getStartNode());
        var nodesDistances = unweightedGraphService.getNodesWithDistances(graphRequest.getGraph(), graphRequest.getStartNode());
        var nodesResponse = new NodesListResponse(nodesDistances);
        return ResponseEntity.ok(nodesResponse);
    }

    // todo - add error handling (Error Handler)
}
