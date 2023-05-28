package com.streisky.discordchatgptbot.command.ping;

import com.streisky.discordchatgptbot.command.CommandInterface;
import com.streisky.discordchatgptbot.message.MessageModel;

import java.util.List;

public class PingCommand implements CommandInterface {

    @Override
    public List<MessageModel> execute(String content) {
        return List.of(new MessageModel("Pong!"));
    }
}
