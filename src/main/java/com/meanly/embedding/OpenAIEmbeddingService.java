package com.meanly.embedding;

import java.util.ArrayList;
import java.util.List;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

// Fetches semantic vectors from OpenAI
public class OpenAIEmbeddingService {
  private static final String URL = "https://api.openai.com/v1/embeddings";
  private final OkHttpClient client = new OkHttpClient();
  private final String key, model;

  public OpenAIEmbeddingService(String key, String model) {
    this.key = key;
    this.model = model;
  }

  public List<Double> embed(String text) {
    try {
      JSONObject body = new JSONObject().put("model", model).put("input", text);
      Request req =
          new Request.Builder()
              .url(URL)
              .addHeader("Authorization", "Bearer " + key)
              .post(RequestBody.create(body.toString(), MediaType.parse("application/json")))
              .build();

      try (Response res = client.newCall(req).execute()) {
        if (!res.isSuccessful()) throw new RuntimeException("API Error: " + res.code());

        JSONArray arr =
            new JSONObject(res.body().string())
                .getJSONArray("data")
                .getJSONObject(0)
                .getJSONArray("embedding");

        List<Double> list = new ArrayList<>();
        for (int i = 0; i < arr.length(); i++) list.add(arr.getDouble(i));
        return list;
      }
    } catch (Exception e) {
      throw new RuntimeException("Embedding failed", e);
    }
  }
}
