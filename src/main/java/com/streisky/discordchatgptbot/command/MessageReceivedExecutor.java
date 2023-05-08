package com.streisky.discordchatgptbot.command;

import com.streisky.discordchatgptbot.exception.AuthorIsBotException;
import com.streisky.discordchatgptbot.exception.InvalidCommandException;
import com.streisky.discordchatgptbot.exception.InvalidKeywordException;
import com.streisky.discordchatgptbot.exception.OpenAICommunicationErrorException;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MessageReceivedExecutor extends MessageReceivedAbstract {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        try {
            super.onMessageReceived(event);
            validate(event);

            CommandInterface command = getCommand().getCommandInterface();
            List<String> response = command.execute(getContentWithoutKeyWordAndCommand());
            response.forEach((r) -> messageChannel.sendMessage(r).queue());
        } catch (AuthorIsBotException | InvalidKeywordException e) {
            System.out.println(e.getMessage());
        } catch (InvalidCommandException | OpenAICommunicationErrorException e) {
            messageChannel.sendMessage(e.getMessage()).queue();
        } catch (Exception e) {
            Logger logger = Logger.getLogger(MessageReceivedExecutor.class.getName());
            logger.log(Level.SEVERE, "An error has occurred: ", e);
        }
    }
}
