package com.streisky.discordchatgptbot.command;

import com.streisky.discordchatgptbot.exception.OpenAICommunicationErrorException;
import com.streisky.discordchatgptbot.openai.OpenAIApi;

import java.util.ArrayList;
import java.util.List;

public class ChatGPTCommand implements CommandInterface {

    @Override
    public List<String> execute(String content) throws OpenAICommunicationErrorException {
        try {
            String openAIResponse = OpenAIApi.sendPrompt(content);
            return splitStringIntoList(openAIResponse);
        } catch (Exception e) {
            throw new OpenAICommunicationErrorException();
        }
    }

    private static List<String> splitStringIntoList(String input) {
        List<String> list = new ArrayList<>();
        int size = 1500;

        for (int i = 0; i < input.length(); i += size) {
            int endIndex = Math.min(i + size, input.length());
            String string = input.substring(i, endIndex);
            list.add(string);
        }

        return list;
    }
}
