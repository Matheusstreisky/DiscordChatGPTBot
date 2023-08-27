package com.streisky.discordchatgptbot.message;

import com.streisky.discordchatgptbot.command.CommandInterface;
import com.streisky.discordchatgptbot.exception.*;
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
            List<MessageModel> response = command.execute(getContentWithoutKeyWordAndCommand());
            response.forEach((r) -> {
                if (r.getFile() != null)
                    messageChannel.sendMessage(r.getMessage()).addFiles(r.getFile()).queue();
                else
                    messageChannel.sendMessage(r.getMessage()).queue();
            });
        } catch (AuthorIsBotException | InvalidKeywordException e) {
            System.out.println(e.getMessage());
        } catch (InvalidCommandException | OpenAICommunicationErrorException | GetAnimeGirlErrorException e) {
            messageChannel.sendMessage(e.getMessage()).queue();
        } catch (Exception e) {
            Logger logger = Logger.getLogger(MessageReceivedExecutor.class.getName());
            logger.log(Level.SEVERE, "An error has occurred: ", e);
        }
    }
}
