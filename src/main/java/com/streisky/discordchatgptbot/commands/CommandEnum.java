package com.streisky.discordchatgptbot.commands;

import com.streisky.discordchatgptbot.exception.InvalidCommandException;

import java.util.Arrays;

public enum CommandEnum {

    PING("ping"),
    CHATGPT("chatgpt");

    private final String value;

    CommandEnum(String value) {
        this.value = value;
    }

    public static CommandEnum getCommand(String command) throws InvalidCommandException {
        return Arrays.stream(values())
                .filter(c -> c.value.equalsIgnoreCase(command))
                .findFirst()
                .orElseThrow(InvalidCommandException::new);
    }

    @Override
    public String toString() {
        return value;
    }
}
