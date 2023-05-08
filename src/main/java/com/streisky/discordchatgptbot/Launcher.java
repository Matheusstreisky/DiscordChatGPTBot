package com.streisky.discordchatgptbot;

import net.dv8tion.jda.api.JDABuilder;
import com.streisky.discordchatgptbot.commands.PingPongCommand;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Launcher {

    private static final String TOKEN = "MTEwNDgyMjkxNTU3MDA5MDEyNQ.GPCQ6h.Mtc2ajqfxufDFKyURWWxCMSnzW0c0YjXigFCnw";

    public static void main(String[] args) throws InterruptedException {
        JDABuilder.createDefault(TOKEN)
                .setActivity(Activity.playing(Activity.ActivityType.PLAYING.toString()))
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .addEventListeners(
                        new PingPongCommand()
                )
                .build()
                .awaitReady();
    }
}
