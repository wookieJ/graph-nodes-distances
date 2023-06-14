package io.mend.interview.domain.model;

import java.util.List;

public class NodesListResponse {
    private List<NodeResponse> result;

    public List<NodeResponse> getResult() {
        return result;
    }

    public void setResult(List<NodeResponse> result) {
        this.result = result;
    }

    public NodesListResponse(List<NodeResponse> result) {
        this.result = result;
    }
}
