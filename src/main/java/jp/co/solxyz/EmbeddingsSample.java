package jp.co.solxyz;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.ai.openai.models.EmbeddingItem;
import com.azure.ai.openai.models.Embeddings;
import com.azure.ai.openai.models.EmbeddingsOptions;
import com.azure.core.credential.KeyCredential;

import java.util.List;

/**
 * Embeddingsの例
 */
public class EmbeddingsSample {

    public static void main(String[] args) {
        String key = System.getenv("OPENAI_API_KEY");
        // OpenAIクライアントの生成
        OpenAIClient client = new OpenAIClientBuilder()
                .credential(new KeyCredential(key))
                .buildClient();

        // Embeddings対象のテキストを用意
        EmbeddingsOptions embeddingsOptions = new EmbeddingsOptions(
                List.of("Hello World"));

        // モデルを指定してEmbeddingsを行う
        Embeddings embeddings = client.getEmbeddings("text-embedding-ada-002", embeddingsOptions);

        for (EmbeddingItem item : embeddings.getData()) {
            for (Float embedding : item.getEmbedding()) {
                System.out.printf("%f;", embedding);
            }
        }
    }
}
