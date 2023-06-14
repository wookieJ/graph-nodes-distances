package io.mend.interview.domain.model;


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

    @Override
    public String toString() {
        return "Graph{" +
                "graph=" + graph +
                ", startNode='" + startNode + '\'' +
                '}';
    }
}
