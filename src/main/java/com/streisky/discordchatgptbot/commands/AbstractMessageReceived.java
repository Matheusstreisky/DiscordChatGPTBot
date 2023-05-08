package com.streisky.discordchatgptbot.commands;

import com.streisky.discordchatgptbot.exception.AuthorIsBotException;
import com.streisky.discordchatgptbot.exception.InvalidCommandException;
import com.streisky.discordchatgptbot.exception.InvalidKeywordException;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public abstract class AbstractMessageReceived extends ListenerAdapter {

    private static final String KEY_WORD = "!xinxila";

    protected Message message;
    protected String content;
    protected String[] arguments;
    protected MessageChannel messageChannel;

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        message = event.getMessage();
        content = message.getContentRaw();
        arguments = content.split(" ");
        messageChannel = event.getChannel();
    }

    public void validate(MessageReceivedEvent event) throws Exception {
        if (event != null && event.getAuthor().isBot())
            throw new AuthorIsBotException();

        if (arguments.length > 0 && !getKeyWord().equals(KEY_WORD))
            throw new InvalidKeywordException();
    }

    public String getKeyWord() {
        return arguments[0].toLowerCase();
    }

    public CommandEnum getCommand() throws InvalidCommandException {
        return CommandEnum.getCommand(arguments[1].toLowerCase());
    }

    public String getContentWithoutKeyWordAndCommand() {
        String contentWithoutKeyWordAndCommand = content.replace(arguments[0] + " ", "");
        contentWithoutKeyWordAndCommand = contentWithoutKeyWordAndCommand.replace(arguments[1] + " ", "");

        return content = contentWithoutKeyWordAndCommand;
    }
}
