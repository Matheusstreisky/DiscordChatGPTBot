package com.streisky.discordchatgptbot.command;

import java.util.List;

public interface CommandInterface {

    List<String> execute(String content) throws Exception;
}
