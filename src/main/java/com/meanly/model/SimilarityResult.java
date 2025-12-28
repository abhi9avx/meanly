package com.meanly.model;

public class SimilarityResult {

    private final double similarity;
    private final double distance;
    private final double angle;

    public SimilarityResult(double similarity, double distance, double angle) {
        this.similarity = similarity;
        this.distance = distance;
        this.angle = angle;
    }

    public double getSimilarity() {
        return similarity;
    }

    public double getDistance() {
        return distance;
    }

    public double getAngle() {
        return angle;
    }

    @Override
    public String toString() {
        return "SimilarityResult{" +
                "similarity=" + similarity +
                ", distance=" + distance +
                ", angle=" + angle +
                '}';
    }
}
