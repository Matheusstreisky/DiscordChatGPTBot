package com.streisky.discordchatgptbot.command.chatgpt;

import com.streisky.discordchatgptbot.command.CommandInterface;
import com.streisky.discordchatgptbot.exception.OpenAICommunicationErrorException;
import com.streisky.discordchatgptbot.message.MessageModel;
import com.streisky.discordchatgptbot.openai.OpenAIApi;

import java.util.ArrayList;
import java.util.List;

public class ChatGPTCommand implements CommandInterface {

    @Override
    public List<MessageModel> execute(String content) throws OpenAICommunicationErrorException {
        try {
            String openAIResponse = OpenAIApi.sendPrompt(content);
            return splitStringIntoList(openAIResponse);
        } catch (Exception e) {
            throw new OpenAICommunicationErrorException();
        }
    }

    private static List<MessageModel> splitStringIntoList(String input) {
        List<MessageModel> list = new ArrayList<>();
        int size = 1500;

        for (int i = 0; i < input.length(); i += size) {
            int endIndex = Math.min(i + size, input.length());
            String string = input.substring(i, endIndex);
            MessageModel messageModel = new MessageModel(string);
            list.add(messageModel);
        }

        return list;
    }
}
