package com.streisky.discordchatgptbot.command;

import java.util.List;

public class PingPongCommand implements CommandInterface {

    @Override
    public List<String> execute(String content) {
        return List.of("Pong!");
    }
}
