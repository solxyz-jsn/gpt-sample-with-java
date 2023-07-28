package jp.co.solxyz;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.ai.openai.models.EmbeddingItem;
import com.azure.ai.openai.models.Embeddings;
import com.azure.ai.openai.models.EmbeddingsOptions;
import com.azure.ai.openai.models.NonAzureOpenAIKeyCredential;

import java.util.Arrays;

/**
 * Embeddingsの例
 */
public class EmbeddingsSample {

    /**
     * OpenAIのAPIキー
     */
    private static final String OPENAI_API_KEY = "あなたのアクセスキー";

    public static void main(String[] args) {
        // OpenAIクライアントの生成
        OpenAIClient client = new OpenAIClientBuilder()
                .credential(new NonAzureOpenAIKeyCredential(OPENAI_API_KEY))
                .buildClient();

        // Embeddings対象のテキストを用意
        EmbeddingsOptions embeddingsOptions = new EmbeddingsOptions(
                Arrays.asList("Hello World"));

        // モデルを指定してEmbeddingsを行う
        Embeddings embeddings = client.getEmbeddings("text-embedding-ada-002", embeddingsOptions);

        for (EmbeddingItem item : embeddings.getData()) {
            for (Double embedding : item.getEmbedding()) {
                System.out.printf("%f;", embedding);
            }
        }
    }
}
