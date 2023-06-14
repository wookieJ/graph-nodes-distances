package graph.nodes.domain.model;

public class Edge {
    private final String destNode;

    private final EdgeType type;

    public String getDestNode() {
        return destNode;
    }

    public EdgeType getType() {
        return type;
    }

    public Edge(String id, EdgeType type) {
        this.destNode = id;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id='" + destNode + '\'' +
                ", type=" + type +
                '}';
    }
}
