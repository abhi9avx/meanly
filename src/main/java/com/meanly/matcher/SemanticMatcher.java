package com.meanly.matcher;

import com.meanly.embedding.EmbeddingService;
import com.meanly.model.SimilarityResult;
import com.meanly.similarity.CosineSimilarityEngine;
import com.meanly.similarity.SimilarityEngine;

import java.util.List;

public class SemanticMatcher {

    private final EmbeddingService embeddingService;
    private final SimilarityEngine similarityEngine;

    public SemanticMatcher(EmbeddingService embeddingService) {
        this.embeddingService = embeddingService;
        this.similarityEngine = new CosineSimilarityEngine();
    }

    public SimilarityResult compare(String text1, String text2) {

        List<Double> vector1 = embeddingService.embed(text1);
        List<Double> vector2 = embeddingService.embed(text2);

        double similarity = similarityEngine.calculate(vector1, vector2);
        double distance = 1 - similarity;
        double angle = ((CosineSimilarityEngine) similarityEngine)
                .angleInDegrees(similarity);

        return new SimilarityResult(similarity, distance, angle);
    }
}
