package com.streisky.discordchatgptbot.utils;

import com.google.gson.Gson;

public class JsonUtils {

    public static <T> JsonCompatible convertJsonToObject(Class<? extends JsonCompatible> c, String json) {
        return new Gson().fromJson(json, c);
    }
}
