package com.streisky.discordchatgptbot.command.animegirl;

import com.streisky.discordchatgptbot.animegirlwithbooks.AnimeGirlWithBooksApi;
import com.streisky.discordchatgptbot.command.CommandInterface;
import com.streisky.discordchatgptbot.exception.GetAnimeGirlErrorException;
import com.streisky.discordchatgptbot.message.MessageModel;
import net.dv8tion.jda.api.utils.FileUpload;

import java.io.File;
import java.util.List;

public class AnimeGirlCommand implements CommandInterface {

    @Override
    public List<MessageModel> execute(String content) throws Exception {
        try {
            String[] parameters = content.split(" ");
            String message = "Here is anime girl for you!";
            File file = AnimeGirlWithBooksApi.getImageAnimeGirl(getFilter(parameters), getWidth(parameters), getHeight(parameters));
            FileUpload fileUpload = FileUpload.fromData(file);
            MessageModel messageModel = new MessageModel(message, fileUpload);

            return List.of(messageModel);
        } catch (Exception e) {
            throw new GetAnimeGirlErrorException();
        }
    }

    private String getFilter(String[] parameters){
        return parameters.length == 1 && !parameters[0].isEmpty() ? parameters[0] : null;
    }

    private String getWidth(String[] parameters){
        return parameters.length == 2 && !parameters[0].isEmpty() ? parameters[0] : null;
    }

    private String getHeight(String[] parameters){
        return parameters.length == 2 && !parameters[1].isEmpty() ? parameters[1] : null;
    }
}
