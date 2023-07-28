package jp.co.solxyz;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.ai.openai.models.*;

import java.util.ArrayList;
import java.util.List;

/**
 * シンプルなクエリの例
 */
public class SimpleQuery {

    /**
     * OpenAIのAPIキー
     */
    private static final String OPENAI_API_KEY = "あなたのアクセスキー";

    /**
     * OpenAI APIへ送信するプロンプト
     */
    private static final String PROMPT = "your name?";

    public static void main(String[] args) {
        // OpenAI APIクライアントの作成
        OpenAIClient client = new OpenAIClientBuilder()
                .credential(new NonAzureOpenAIKeyCredential(OPENAI_API_KEY))
                .buildClient();

        // APIに送る会話情報を設定
        List<ChatMessage> chatMessages = new ArrayList<>();
        ChatMessage chat = new ChatMessage(ChatRole.USER);
        chat.setContent(PROMPT);
        chatMessages.add(chat);

        System.out.println("prompt: "+PROMPT);

        // モデルを指定してAPIコール
        ChatCompletions chatCompletions = client.getChatCompletions("gpt-3.5-turbo",
                new ChatCompletionsOptions(chatMessages));

        // レスポンスの表示
        for (ChatChoice choice : chatCompletions.getChoices()) {
            ChatMessage message = choice.getMessage();
            System.out.println("message: " + message.getContent());
        }
    }
}
