package graph.nodes.domain.model;

import java.util.Objects;

public record Distance(Long distance, Long redEdgesNumber) implements Comparable<Distance> {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Distance distance1)) return false;
        return Objects.equals(distance, distance1.distance) && Objects.equals(redEdgesNumber, distance1.redEdgesNumber);
    }

    @Override
    public int compareTo(Distance otherDistance) {
        if (otherDistance.redEdgesNumber() > 1) {
            return -1;
        } else if (this.redEdgesNumber() > 1) {
            return 1;
        }
        return Long.compare(this.distance(), otherDistance.distance());
    }
}
