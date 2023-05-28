package com.streisky.discordchatgptbot.command.animegirl;

import com.streisky.discordchatgptbot.animegirlwithbooks.AnimeGirlWithBooksApi;
import com.streisky.discordchatgptbot.command.CommandInterface;
import com.streisky.discordchatgptbot.exception.GetAnimeGirlErrorException;
import com.streisky.discordchatgptbot.exception.InvalidCommandException;
import com.streisky.discordchatgptbot.message.MessageModel;
import net.dv8tion.jda.api.utils.FileUpload;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

public class AnimeGirlCommand implements CommandInterface {

    private static final String COMMAND_HELP = "-help";
    private static final String COMMAND_FILTER = "-filter";
    private static final String COMMAND_SIZE = "-size";
    private static final List<String> LIST_COMMANDS = List.of(COMMAND_HELP, COMMAND_FILTER, COMMAND_SIZE);

    @Override
    public List<MessageModel> execute(String content) throws Exception {
        try {
            String[] parameters = content.split(" ");

            if (!validateCommand(parameters) ||
                    (getCommand(parameters).equalsIgnoreCase(COMMAND_FILTER) && !validateFilter(parameters)) ||
                    (getCommand(parameters).equalsIgnoreCase(COMMAND_SIZE) && !validateSize(parameters)))
                throw new InvalidCommandException();

            if (getCommand(parameters).equalsIgnoreCase(COMMAND_HELP))
                return getAnimeGirlHelpResponse();

            return getAnimeGirlResponse(parameters);
        } catch (InvalidCommandException e) {
            throw new InvalidCommandException();
        } catch (Exception e) {
            throw new GetAnimeGirlErrorException();
        }
    }

    private boolean validateCommand(String[] parameters) throws URISyntaxException, IOException, InterruptedException {
        String command = getCommand(parameters);
        return command.isEmpty() || LIST_COMMANDS.contains(command);
    }

    private boolean validateFilter(String[] parameters) throws URISyntaxException, IOException, InterruptedException {
        List<String> listLanguages = AnimeGirlWithBooksApi.getListLanguages();
        return listLanguages.contains(getFilter(parameters));
    }

    private boolean validateSize(String[] parameters) {
        try {
            Integer.parseInt(Objects.requireNonNull(getWidth(parameters)));
            Integer.parseInt(Objects.requireNonNull(getHeight(parameters)));
            return true;
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
    }

    private List<MessageModel> getAnimeGirlHelpResponse() throws URISyntaxException, IOException, InterruptedException {
        String messageListCommands = formatText(
                "Here is the list of available commands: " + System.lineSeparator() +
                String.join(System.lineSeparator(), LIST_COMMANDS)
        );

        List<String> languages = AnimeGirlWithBooksApi.getListLanguages();
        String messageListLanguagues = formatText(
                "- Filter list:" + System.lineSeparator() +
                String.join(System.lineSeparator(), languages)
        );

        return List.of(
                new MessageModel(messageListCommands),
                new MessageModel(messageListLanguagues)
        );
    }

    private List<MessageModel> getAnimeGirlResponse(String[] parameters) throws URISyntaxException, IOException, InterruptedException {
        String message = "Here is anime girl for you!";
        File file = AnimeGirlWithBooksApi.getImageAnimeGirl(getFilter(parameters), getWidth(parameters), getHeight(parameters));
        FileUpload fileUpload = FileUpload.fromData(file);

        return List.of(new MessageModel(message, fileUpload));
    }

    private String formatText(String text ) {
        final String TEXT_FORMATTER = "```";
        return TEXT_FORMATTER + text + TEXT_FORMATTER;
    }

    private String getCommand(String[] parameters) {
        return parameters.length >= 1 && !parameters[0].isEmpty() ? parameters[0] : "";
    }

    private String getFilter(String[] parameters){
        return parameters.length == 2 && !parameters[1].isEmpty() ? parameters[1] : null;
    }

    private String getWidth(String[] parameters){
        return parameters.length == 3 && !parameters[1].isEmpty() ? parameters[1] : null;
    }

    private String getHeight(String[] parameters){
        return parameters.length == 3 && !parameters[2].isEmpty() ? parameters[2] : null;
    }
}
