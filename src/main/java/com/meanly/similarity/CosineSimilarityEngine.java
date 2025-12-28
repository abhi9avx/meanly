package com.meanly.similarity;

import java.util.List;

public class CosineSimilarityEngine implements SimilarityEngine {

    @Override
    public double calculate(List<Double> v1, List<Double> v2) {

        if (v1 == null || v2 == null) {
            throw new IllegalArgumentException("Vectors must not be null");
        }

        if (v1.size() != v2.size()) {
            throw new IllegalArgumentException("Vector dimensions must match");
        }

        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;

        for (int i = 0; i < v1.size(); i++) {
            double a = v1.get(i);
            double b = v2.get(i);

            dotProduct += a * b;
            normA += a * a;
            normB += b * b;
        }

        return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
    }

    /**
     * Angle between vectors in degrees (for explainability)
     */
    public double angleInDegrees(double similarity) {
        return Math.toDegrees(
                Math.acos(Math.max(-1.0, Math.min(1.0, similarity)))
        );
    }
}
