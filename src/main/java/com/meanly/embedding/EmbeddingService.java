package com.meanly.embedding;

import java.util.List;

public interface EmbeddingService {

    /**
     * Generates vector embedding for the given text
     *
     * @param text input text
     * @return vector representation
     */
    List<Double> embed(String text);
}
