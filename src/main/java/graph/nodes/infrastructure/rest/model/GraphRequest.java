package graph.nodes.infrastructure.rest.model;


import graph.nodes.domain.model.Graph;
import graph.nodes.domain.model.Edge;

import java.util.List;
import java.util.Map;

public class GraphRequest {
    private Map<String, List<Edge>> graph;

    private String startNode;

    public Map<String, List<Edge>> getGraph() {
        return graph;
    }

    public String getStartNode() {
        return startNode;
    }

    public GraphRequest() {
    }

    public Graph toDomain() {
        return new Graph(this.graph);
    }

    @Override
    public String toString() {
        return "Graph{" +
                "graph=" + graph +
                ", startNode='" + startNode + '\'' +
                '}';
    }
}
