package jp.co.solxyz;

import com.azure.ai.openai.models.ChatCompletions;
import com.azure.ai.openai.models.ChatCompletionsOptions;
import com.azure.ai.openai.models.ChatRequestMessage;
import com.azure.ai.openai.models.ChatRequestUserMessage;
import com.azure.ai.openai.models.ChatResponseMessage;
import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.ai.openai.models.ChatChoice;

import java.util.ArrayList;
import java.util.List;

public class AzureOpenAIExample {
    private static final String PROMPT = "your name?";
    public static void main(String[] args) {
        String url = System.getenv("OPEN_AI_URL");
        // Azure CLIで認証した資格情報を使用
        OpenAIClient client = new OpenAIClientBuilder()
                .credential(new DefaultAzureCredentialBuilder().build())
                .endpoint(url)
                .buildClient();

        List<ChatRequestMessage> chatMessages = new ArrayList<>();
        chatMessages.add(new ChatRequestUserMessage(PROMPT));

        System.out.println("prompt: " + PROMPT);

        // 3. モデルを指定してAPIコール
        ChatCompletions chatCompletions = client.getChatCompletions("jsn-gpt-35-turbo",
                new ChatCompletionsOptions(chatMessages));

        // 4. レスポンスの表示
        for (ChatChoice choice : chatCompletions.getChoices()) {
            ChatResponseMessage message = choice.getMessage();
            System.out.println("message: " + message.getContent());
        }
    }
}
