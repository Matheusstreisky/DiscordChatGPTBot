package com.streisky.discordchatgptbot.commands;

import com.streisky.discordchatgptbot.exception.InvalidCommandException;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class PingPongCommand extends AbstractMessageReceived {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        try {
            super.onMessageReceived(event);
            validate(event);

            if (getCommand().equals(CommandEnum.PING))
                messageChannel.sendMessage("Pong!").queue();
        } catch (InvalidCommandException e) {
            messageChannel.sendMessage(e.getMessage()).queue();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
