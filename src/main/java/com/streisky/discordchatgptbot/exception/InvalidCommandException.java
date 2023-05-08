package com.streisky.discordchatgptbot.exception;

public class InvalidCommandException extends Exception {

    public InvalidCommandException() {
        super("Invalid command!");
    }
}
