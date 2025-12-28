package com.meanly.similarity;

import java.util.List;

// Math engine for vector similarity
public class CosineSimilarityEngine {

  public double calculate(List<Double> v1, List<Double> v2) {
    double dot = 0, n1 = 0, n2 = 0;
    for (int i = 0; i < v1.size(); i++) {
      double a = v1.get(i), b = v2.get(i);
      dot += a * b;
      n1 += a * a;
      n2 += b * b;
    }
    return dot / (Math.sqrt(n1) * Math.sqrt(n2));
  }

  // Converts cosine similarity to angle degrees
  public double angleInDegrees(double score) {
    return Math.toDegrees(Math.acos(Math.max(-1.0, Math.min(1.0, score))));
  }
}
