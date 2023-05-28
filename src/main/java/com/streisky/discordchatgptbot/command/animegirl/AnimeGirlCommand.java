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

    private static final String PARAMETER_HELP = "-help";
    private static final String PARAMETER_FILTER = "-filter";
    private static final String PARAMETER_SIZE = "-size";
    private static final List<String> LIST_PARAMETERS = List.of(PARAMETER_HELP, PARAMETER_FILTER, PARAMETER_SIZE);

    @Override
    public List<MessageModel> execute(String content) throws Exception {
        try {
            String[] parameters = content.split(" ");

            if (!validateParameter(parameters) ||
                    (getParameter(parameters).equalsIgnoreCase(PARAMETER_FILTER) && !validateParameterFilter(parameters)) ||
                    (getParameter(parameters).equalsIgnoreCase(PARAMETER_SIZE) && !validateParameterSize(parameters)))
                throw new InvalidCommandException();

            if (getParameter(parameters).equalsIgnoreCase(PARAMETER_HELP))
                return getAnimeGirlHelpResponse();

            return getAnimeGirlResponse(parameters);
        } catch (InvalidCommandException e) {
            throw new InvalidCommandException();
        } catch (Exception e) {
            throw new GetAnimeGirlErrorException();
        }
    }

    private boolean validateParameter(String[] parameters) {
        String parameter = getParameter(parameters);
        return parameter.isEmpty() || LIST_PARAMETERS.contains(parameter);
    }

    private boolean validateParameterFilter(String[] parameters) throws URISyntaxException, IOException, InterruptedException {
        List<String> listLanguages = AnimeGirlWithBooksApi.getListLanguages();
        return listLanguages.contains(getFilter(parameters));
    }

    private boolean validateParameterSize(String[] parameters) {
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
                "Here is the list of available parameters: " + System.lineSeparator() +
                String.join(System.lineSeparator(), LIST_PARAMETERS)
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

    private String getParameter(String[] parameters) {
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
