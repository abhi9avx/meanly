package com.meanly.matcher;

import com.meanly.config.ConfigLoader;
import com.meanly.config.SemanticConfig;
import com.meanly.embedding.OpenAIEmbeddingService;
import com.meanly.model.SimilarityResult;
import com.meanly.similarity.CosineSimilarityEngine;
import java.util.List;

// Main engine for semantically comparing text
public class SemanticMatcher {
  private final OpenAIEmbeddingService embeddings;
  private final CosineSimilarityEngine engine = new CosineSimilarityEngine();
  private final SemanticConfig config;

  public SemanticMatcher() {
    this.config = ConfigLoader.load();
    this.embeddings = new OpenAIEmbeddingService(config.apiKey(), config.model());
  }

  // Compares two strings and returns similarity metrics
  public SimilarityResult compare(String t1, String t2) {
    List<Double> v1 = embeddings.embed(t1);
    List<Double> v2 = embeddings.embed(t2);

    double score = engine.calculate(v1, v2);
    return new SimilarityResult(score, 1 - score, engine.angleInDegrees(score));
  }

  // Returns true if similarity is above the configured threshold
  public boolean isMatch(SimilarityResult res) {
    return res.similarity() >= config.threshold();
  }
}
