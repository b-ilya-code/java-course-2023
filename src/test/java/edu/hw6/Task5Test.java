package edu.hw6;

import edu.hw6.Task5.HackerNews;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Task5Test {
    @Test
    public void titleTest() throws IOException, InterruptedException {
        HttpClient httpClientMock = mock(HttpClient.class);
        HttpResponse<String> httpResponseMock = mock(HttpResponse.class);
        when(httpResponseMock.body()).thenReturn("[1,2,3]");
        when(httpClientMock.send(ArgumentMatchers.any(HttpRequest.class), ArgumentMatchers.any(HttpResponse.BodyHandler.class))).thenReturn(httpResponseMock);
        long[] topStories = HackerNews.hackerNewsTopStories(httpClientMock);

        assertEquals(3, topStories.length);
        assertEquals(1, topStories[0]);
        assertEquals(2, topStories[1]);
        assertEquals(3, topStories[2]);

    }

    @Test
    public void testNews() throws IOException, InterruptedException {
        HttpClient httpClientMock = mock(HttpClient.class);
        HttpResponse<String> httpResponseMock = mock(HttpResponse.class);
        String responseBody = "{\"title\":\"Test\"}";
        when(httpResponseMock.body()).thenReturn(responseBody);
        when(httpClientMock.send(ArgumentMatchers.any(HttpRequest.class), ArgumentMatchers.any(HttpResponse.BodyHandler.class)))
            .thenReturn(httpResponseMock);
        String newsTitle = HackerNews.news(123, httpClientMock);

        assertEquals("Test", newsTitle);
    }
}
