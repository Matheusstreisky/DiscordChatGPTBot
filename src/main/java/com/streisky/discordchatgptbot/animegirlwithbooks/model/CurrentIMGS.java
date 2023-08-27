package com.streisky.discordchatgptbot.animegirlwithbooks.model;

import com.google.gson.annotations.SerializedName;
import com.streisky.discordchatgptbot.utils.JsonCompatible;

import java.util.Map;

public class CurrentIMGS implements JsonCompatible {

    @SerializedName("CurrentIMGS")
    private Map<String, Integer> languages;

    public Map<String, Integer> getLanguages() {
        return languages;
    }
}
