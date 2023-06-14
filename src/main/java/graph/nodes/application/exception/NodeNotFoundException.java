package graph.nodes.application.exception;

public class NodeNotFoundException extends RuntimeException {
    public NodeNotFoundException(String message) {
        super(message);
    }
}
