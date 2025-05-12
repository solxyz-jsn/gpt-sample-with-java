package jp.co.solxyz;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.ai.openai.models.ChatChoice;
import com.azure.ai.openai.models.ChatCompletions;
import com.azure.ai.openai.models.ChatCompletionsOptions;
import com.azure.ai.openai.models.ChatRequestMessage;
import com.azure.ai.openai.models.ChatRequestUserMessage;
import com.azure.ai.openai.models.ChatResponseMessage;
import com.azure.core.credential.KeyCredential;

import java.util.ArrayList;
import java.util.List;

/**
 * シンプルなクエリの例
 */
public class SimpleQuery {

    /**
     * OpenAI APIへ送信するプロンプト
     */
    private static final String PROMPT = "your name?";

    public static void main(String[] args) {
        String key = System.getenv("OPENAI_API_KEY");
        // 1. OpenAI APIクライアントの作成
        OpenAIClient client = new OpenAIClientBuilder()
                .credential(new KeyCredential(key))
                .buildClient();

        // 2. APIに送る会話情報を設定
        List<ChatRequestMessage> chatMessages = new ArrayList<>();
        ChatRequestMessage chat = new ChatRequestUserMessage(PROMPT);
        chatMessages.add(chat);

        System.out.println("prompt: "+PROMPT);

        // 3. モデルを指定してAPIコール
        ChatCompletions chatCompletions = client.getChatCompletions("gpt-4o-mini",
                new ChatCompletionsOptions(chatMessages));

        // 4. レスポンスの表示
        for (ChatChoice choice : chatCompletions.getChoices()) {
            ChatResponseMessage message = choice.getMessage();
            System.out.println("message: " + message.getContent());
        }
    }
}
