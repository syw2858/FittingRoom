package net.nwrn.pf_contest.common.scheduler;

import lombok.RequiredArgsConstructor;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.net.URISyntaxException;

@Component
@RequiredArgsConstructor
public class ScheduledTasks {
    private final WebClient webClient;

    private String personImageUrl = "https://d1hds1xxjs6al7.cloudfront.net/person/452/452";
    private String topImageUrl = "https://d1hds1xxjs6al7.cloudfront.net/top/114/447";
    private String bottomImageUrl = "https://d1hds1xxjs6al7.cloudfront.net/bottom/72/450";

    @Scheduled(cron = "0 0/5 * * * ?")
    public void dummyRequest() throws URISyntaxException {
        URI uri = new URIBuilder().setScheme("http").setHost("localhost:4000").setPath("/fitting").build();

        String requestBody = "{\"my_image\": \"" + personImageUrl + "\", \"upper_clothes\": \"" + topImageUrl + "\", \"lower_clothes\": \"" + bottomImageUrl + "\"}";

        webClient.post().uri(uri).header("Content-Type", "application/json")
                .bodyValue(requestBody)
                .retrieve().bodyToMono(byte[].class).block();
    }
}
