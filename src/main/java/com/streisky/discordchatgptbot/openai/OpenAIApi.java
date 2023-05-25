package com.streisky.discordchatgptbot.openai;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;

public class OpenAIApi {

    private static final String API_KEY = "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";
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
