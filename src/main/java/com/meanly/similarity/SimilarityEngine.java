package com.meanly.similarity;

import java.util.List;

public interface SimilarityEngine {

    /**
     * Calculates similarity between two vectors
     *
     * @param vector1 embedding vector
     * @param vector2 embedding vector
     * @return similarity score (0–1)
     */
    double calculate(List<Double> vector1, List<Double> vector2);
}
