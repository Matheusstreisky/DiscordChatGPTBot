package com.streisky.discordchatgptbot.discord;

import com.streisky.discordchatgptbot.command.MessageReceivedExecutor;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class DiscordApi {

    private static final String TOKEN = "TOKEN";

    public static void initializeBot() throws InterruptedException {
        JDABuilder.createDefault(TOKEN)
                .setActivity(Activity.playing(Activity.ActivityType.PLAYING.toString()))
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .addEventListeners(new MessageReceivedExecutor())
                .build()
                .awaitReady();
    }
}
