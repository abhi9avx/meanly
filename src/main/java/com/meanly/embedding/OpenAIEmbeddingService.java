package com.meanly.embedding;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OpenAIEmbeddingService implements EmbeddingService {

    private static final String OPENAI_EMBEDDING_URL =
            "https://api.openai.com/v1/embeddings";

    private final OkHttpClient httpClient = new OkHttpClient();
    private final String apiKey;
    private final String model;

    public OpenAIEmbeddingService(String apiKey, String model) {
        this.apiKey = apiKey;
        this.model = model;
    }

    @Override
    public List<Double> embed(String text) {
        try {
            JSONObject requestBody = new JSONObject();
            requestBody.put("model", model);
            requestBody.put("input", text);

            Request request = new Request.Builder()
                    .url(OPENAI_EMBEDDING_URL)
                    .addHeader("Authorization", "Bearer " + apiKey)
                    .addHeader("Content-Type", "application/json")
                    .post(RequestBody.create(
                            requestBody.toString(),
                            MediaType.parse("application/json")
                    ))
                    .build();

            Response response = httpClient.newCall(request).execute();

            if (!response.isSuccessful()) {
                throw new RuntimeException(
                        "OpenAI API error: " + response.code());
            }

            String responseBody = response.body().string();

            JSONArray embeddingArray = new JSONObject(responseBody)
                    .getJSONArray("data")
                    .getJSONObject(0)
                    .getJSONArray("embedding");

            List<Double> embedding = new ArrayList<>();
            for (int i = 0; i < embeddingArray.length(); i++) {
                embedding.add(embeddingArray.getDouble(i));
            }

            return embedding;

        } catch (Exception e) {
            throw new RuntimeException("Failed to generate embedding", e);
        }
    }
}
