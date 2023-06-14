package graph.nodes.infrastructure.rest.model;

public class NodeResponse {
    private String id;

    private Long distance;

    public String getId() {
        return id;
    }

    public Long getDistance() {
        return distance;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    public NodeResponse(String id, Long distance) {
        this.id = id;
        this.distance = distance;
    }
}
