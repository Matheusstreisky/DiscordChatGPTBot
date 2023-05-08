package com.streisky.discordchatgptbot.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class PingPongCommand extends AbstractMessageReceived {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        try {
            super.onMessageReceived(event);
            validate(event);

            if (content.contains("ping"))
                messageChannel.sendMessage("Pong!").queue();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
