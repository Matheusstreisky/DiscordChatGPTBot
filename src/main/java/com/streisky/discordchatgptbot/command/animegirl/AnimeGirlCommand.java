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
            String message = "Here is anime girl for you!";
            File file = AnimeGirlWithBooksApi.getImageAnimeGirl(getFilter(content), null, null);
            FileUpload fileUpload = FileUpload.fromData(file);
            MessageModel messageModel = new MessageModel(message, fileUpload);

            return List.of(messageModel);
        } catch (Exception e) {
            throw new GetAnimeGirlErrorException();
        }
    }

    private String getFilter(String filter){
        return !filter.isEmpty() ? filter : null;
    }
}
