package graph.nodes.domain.model;

import java.util.Objects;

public class Distance implements Comparable<Distance> {
    private final Long distance;
    private final Long redEdgesNumber;

    public Distance(Long distance, Long redEdgesNumber) {
        this.distance = distance;
        this.redEdgesNumber = redEdgesNumber;
    }

    public Long getDistance() {
        return distance;
    }

    public Long getRedEdgesNumber() {
        return redEdgesNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Distance distance)) return false;
        return Objects.equals(this.distance, distance.distance) && Objects.equals(redEdgesNumber, distance.redEdgesNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(distance, redEdgesNumber);
    }

    @Override
    public int compareTo(Distance otherDistance) {
        if (otherDistance.getRedEdgesNumber() > 1) {
            return -1;
        } else if (this.getRedEdgesNumber() > 1) {
            return 1;
        }
        return Long.compare(this.getDistance(), otherDistance.getDistance());
    }
}
