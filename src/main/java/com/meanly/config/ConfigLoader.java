package com.meanly.config;

import java.io.InputStream;
import java.util.Properties;

// Loads settings from ENV or meanly.properties
public class ConfigLoader {
  private static final String FILE = "meanly.properties";

  public static SemanticConfig load() {
    Properties props = new Properties();
    try (InputStream in = ConfigLoader.class.getClassLoader().getResourceAsStream(FILE)) {
      if (in != null) props.load(in);
    } catch (Exception e) {
      throw new RuntimeException("Config load failed", e);
    }

    // Use ENV variable if present, otherwise fallback to properties file
    String key = System.getenv("OPENAI_API_KEY");
    if (key == null || key.isEmpty()) key = props.getProperty("openai.api.key");

    String model = props.getProperty("openai.model", "text-embedding-3-small");
    double threshold = Double.parseDouble(props.getProperty("similarity.threshold", "0.75"));

    if (key == null || key.isEmpty()) throw new RuntimeException("API Key missing!");

    return new SemanticConfig(key, model, threshold);
  }
}
