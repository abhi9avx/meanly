package com.meanly.config;

// Configuration values for the semantic matcher
public record SemanticConfig(String apiKey, String model, double threshold) {}
