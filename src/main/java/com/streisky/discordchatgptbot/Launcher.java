package com.streisky.discordchatgptbot;

import com.streisky.discordchatgptbot.discord.DiscordApi;

public class Launcher {

    public static void main(String[] args) throws InterruptedException {
        DiscordApi.initializeBot();
    }
}
