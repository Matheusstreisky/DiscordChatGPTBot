package com.streisky.discordchatgptbot.animegirlwithbooks;

import com.streisky.discordchatgptbot.animegirlwithbooks.model.CurrentIMGS;
import com.streisky.discordchatgptbot.utils.JsonUtils;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class AnimeGirlWithBooksApi {

    private static final String URI = "http://localhost:3000/";
    private static final String URI_API = URI + "api/";

    public static File getImageAnimeGirl(String filter, String width, String height) throws URISyntaxException, IOException, InterruptedException {
        byte[] image = getImageFromApi(mountURI(filter, width, height));
        File file = new File("animeGirlWithBooks.jpg");
        Files.write(file.toPath(), Objects.requireNonNull(image));

        return file;
    }

    private static byte[] getImageFromApi(String URI) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = getHttpRequest(URI);
        HttpResponse<byte[]> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofByteArray());

        return response.statusCode() == 200 ? response.body() : null;
    }

    public static List<String> getListLanguages() throws URISyntaxException, IOException, InterruptedException {
        CurrentIMGS currentIMGS = getCurrentIMGSFromApi();
        Map<String, Integer> languages = Objects.requireNonNull(currentIMGS).getLanguages();

        return new ArrayList<>(languages.keySet());
    }

    private static CurrentIMGS getCurrentIMGSFromApi() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = getHttpRequest(URI);
        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());

        return response.statusCode() == 200 ?
                (CurrentIMGS) JsonUtils.convertJsonToObject(CurrentIMGS.class, response.body()) :
                null;
    }

    private static HttpRequest getHttpRequest(String URI) throws URISyntaxException {
        return HttpRequest.newBuilder()
                .uri(new URI(URI))
                .version(HttpClient.Version.HTTP_2)
                .GET()
                .build();
    }

    private static String mountURI(String filter, String width, String height) {
        return URI_API + mountURIFilter(filter) + mountURISize(width, height);
    }

    private static String mountURIFilter(String filter) {
        return filter!= null ? "?filter=" + filter : "";
    }

    private static String mountURISize(String width, String height) {
        return width != null && height != null ? "?size=" + width + "," + height : "";
    }
}
