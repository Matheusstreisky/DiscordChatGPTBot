package com.streisky.discordchatgptbot.command;

import com.streisky.discordchatgptbot.message.MessageModel;

import java.util.List;

public interface CommandInterface {

    List<MessageModel> execute(String content) throws Exception;
}
