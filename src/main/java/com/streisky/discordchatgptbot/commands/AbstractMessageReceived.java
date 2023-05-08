package com.streisky.discordchatgptbot.commands;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public abstract class AbstractMessageReceived extends ListenerAdapter {

    protected Message message;
    protected String content;
    protected MessageChannel messageChannel;

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        message = event.getMessage();
        content = message.getContentRaw();
        messageChannel = event.getChannel();
    }

    public void validate(MessageReceivedEvent event) throws Exception {
        if (event != null && event.getAuthor().isBot())
            throw new Exception();

        if (!content.isEmpty() && !content.startsWith("!chat"))
            throw new Exception();
    }
}
