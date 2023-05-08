package com.streisky.discordchatgptbot.exception;

public class AuthorIsBotException extends Exception {

    public AuthorIsBotException() {
        super("The author of the message is a bot.");
    }
}
