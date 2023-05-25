package com.streisky.discordchatgptbot.animegirlwithbooks;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.util.Objects;

public class AnimeGirlWithBooksApi {

    private static final String URI = "http://localhost:3000/api/";

    public static File getImageAnimeGirl(String filter, String width, String height) throws URISyntaxException, IOException, InterruptedException {
        byte[] image = getImageFromApi(mountURI(filter, width, height));
        File file = new File("animeGirlWithBooks.jpg");
        Files.write(file.toPath(), Objects.requireNonNull(image));

        return file;
    }

    private static byte[] getImageFromApi(String URI) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(URI))
                .version(HttpClient.Version.HTTP_2)
                .GET()
                .build();

        HttpResponse<byte[]> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofByteArray());

        return response.statusCode() == 200 ? response.body() : null;
    }

    private static String mountURI(String filter, String width, String height) {
        return URI + mountURIFilter(filter) + mountURISize(width, height);
    }

    private static String mountURIFilter(String filter) {
        return filter!= null ? "?filter=" + filter : "";
    }

    private static String mountURISize(String width, String height) {
        return width != null && height != null ? "?size=" + width + "," + height : "";
    }
}
