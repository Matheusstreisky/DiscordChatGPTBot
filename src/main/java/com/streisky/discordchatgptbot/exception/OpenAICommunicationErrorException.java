package com.streisky.discordchatgptbot.exception;

public class OpenAICommunicationErrorException extends Exception {

    public OpenAICommunicationErrorException() {
        super("Error communicating with the OpenAI server!");
    }
}
