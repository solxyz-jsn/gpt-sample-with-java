package jp.co.solxyz;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.ai.openai.models.EmbeddingItem;
import com.azure.ai.openai.models.Embeddings;
import com.azure.ai.openai.models.EmbeddingsOptions;
import com.azure.identity.DefaultAzureCredentialBuilder;

import java.util.List;

/**
 * Embeddingsの例
 */
public class EmbeddingsSample {

    public static void main(String[] args) {
        String url = System.getenv("OPEN_AI_URL");
        // 1. Azure OpenAI クライアントの生成
        OpenAIClient client = new OpenAIClientBuilder()
                .credential(new DefaultAzureCredentialBuilder().build())
                .endpoint(url)
                .buildClient();

        // 2. Embeddings対象のテキストを用意
        EmbeddingsOptions embeddingsOptions = new EmbeddingsOptions(
                List.of("Hello World"));

        // 3. Azure OpenAI サービスでデプロイした埋め込みモデルを指定してEmbeddingsを行う
        Embeddings embeddings = client.getEmbeddings("jsn-text-embedding-ada-002", embeddingsOptions);

        // 4. レスポンスの表示
        for (EmbeddingItem item : embeddings.getData()) {
            for (Float embedding : item.getEmbedding()) {
                System.out.printf("%f;", embedding);
            }
        }
    }
}
