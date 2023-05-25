package com.streisky.discordchatgptbot.exception;

public class GetAnimeGirlErrorException extends Exception {

    public GetAnimeGirlErrorException() {
        super("Error when trying to get an image of an anime girl for you!");
    }
}
