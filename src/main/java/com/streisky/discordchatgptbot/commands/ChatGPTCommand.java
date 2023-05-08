package com.streisky.discordchatgptbot.commands;

import com.streisky.discordchatgptbot.exception.AuthorIsBotException;
import com.streisky.discordchatgptbot.exception.InvalidCommandException;
import com.streisky.discordchatgptbot.exception.InvalidKeywordException;
import com.streisky.discordchatgptbot.openai.OpenAIApi;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Arrays;

public class ChatGPTCommand extends AbstractMessageReceived {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        try {
            super.onMessageReceived(event);
            validate(event);

            if (getCommand().equals(CommandEnum.CHATGPT)) {
                String openAIResponse = OpenAIApi.sendPrompt(getContentWithoutKeyWordAndCommand());
                Arrays.stream(openAIResponse.split("(?<=\\G.{100})")).forEach((s) -> {
                    messageChannel.sendMessage(s).queue();
                });
            }
        } catch (AuthorIsBotException | InvalidKeywordException e) {
            System.out.println(e.getMessage());
        } catch (InvalidCommandException e) {
            messageChannel.sendMessage(e.getMessage()).queue();
        } catch (Exception e) {
            messageChannel.sendMessage("Error communicating with the OpenAI server!").queue();
        }
    }
}
