package com.streisky.discordchatgptbot.openai;

import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionRequest;

public class OpenAIApi {

    private static final String API_KEY = "API_KEY";
    private static final String MODEL = "text-davinci-003";
    private static final OpenAiService service = new OpenAiService(API_KEY);

    public static String sendPrompt(String prompt) {
        CompletionRequest request = CompletionRequest
                .builder()
                .prompt(prompt)
                .model(MODEL)
                .maxTokens(2000)
                .temperature(0.5)
                .build();

        return service.createCompletion(request).getChoices().get(0).getText();
    }
}
