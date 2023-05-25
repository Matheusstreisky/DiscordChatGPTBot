package com.streisky.discordchatgptbot.command;

import com.streisky.discordchatgptbot.command.animegirl.AnimeGirlCommand;
import com.streisky.discordchatgptbot.command.chatgpt.ChatGPTCommand;
import com.streisky.discordchatgptbot.command.ping.PingCommand;
import com.streisky.discordchatgptbot.exception.InvalidCommandException;

import java.util.Arrays;

public enum Command {

    PING("ping", new PingCommand()),
    CHATGPT("chatgpt", new ChatGPTCommand()),
    ANIME_GIRL("animegirl", new AnimeGirlCommand());

    private final String value;
    private final CommandInterface commandInterface;

    Command(String value, CommandInterface commandInterface) {
        this.value = value;
        this.commandInterface = commandInterface;
    }

    public static Command getCommand(String command) throws InvalidCommandException {
        return Arrays.stream(values())
                .filter(c -> c.value.equalsIgnoreCase(command))
                .findFirst()
                .orElseThrow(InvalidCommandException::new);
    }

    public String getValue() {
        return value;
    }

    public CommandInterface getCommandInterface() {
        return commandInterface;
    }
}
