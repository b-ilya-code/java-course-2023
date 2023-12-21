package edu.hw6.Task5;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.util.Strings;

public final class HackerNews {
    private static final String URL = "https://hacker-news.firebaseio.com/v0/";
    private static final String STORIES_URL = URL + "topstories.json";
    private static final String ID_URL = URL + "item/";

    private HackerNews() {

    }

    public static long[] hackerNewsTopStories(HttpClient httpClient) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(STORIES_URL))
                .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            String[] ids = response.body().replace("[", "").replace("]", "").split(",");
            long[] topStories = new long[ids.length];
            for (int i = 0; i < ids.length; i++) {
                topStories[i] = Long.parseLong(ids[i]);
            }

            return topStories;
        } catch (IOException | InterruptedException e) {
            return new long[0];
        }
    }

    public static String news(long id, HttpClient httpClient) {
        String idUrl = ID_URL + id + ".json";
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(idUrl))
                .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            Pattern pattern = Pattern.compile("\"title\":\"(.*?)\"");
            Matcher matcher = pattern.matcher(response.body());

            if (matcher.find()) {
                return matcher.group(1);
            }
        } catch (IOException | InterruptedException ignored) {
        }
        return Strings.EMPTY;
    }
}
