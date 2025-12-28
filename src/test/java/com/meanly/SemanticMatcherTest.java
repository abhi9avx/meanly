package com.meanly;

import static org.junit.jupiter.api.Assertions.*;

import com.meanly.matcher.SemanticMatcher;
import com.meanly.model.SimilarityResult;
import org.junit.jupiter.api.Test;

public class SemanticMatcherTest {

  @Test
  void shouldMatchSemanticallySimilarTexts() {
    SemanticMatcher matcher = new SemanticMatcher();

    // Testing Hinglish and mixed formats
    String text1 = "aapke account mai 2,00,000 rupyaa hai";
    String text2 = "aapke account mai two hundred thousand rupyaa hai";

    SimilarityResult result = matcher.compare(text1, text2);
    System.out.println("Result: " + result);

    assertNotNull(result);
    assertTrue(result.similarity() > 0.75, "Similarity should be high for same meaning");
  }
}
