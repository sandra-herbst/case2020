package project;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadRequest {

    private static Logger log = LogManager.getLogger(ThreadRequest.class);

    public void runSchedule(){
        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(this::httpGET, 0, 20, TimeUnit.MINUTES);
        log.debug("Schedule running");
    }

    private void httpGET() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .header("Authorization", "Basic ZXNjYXBlOmphbnNs")
                    .timeout(Duration.ofSeconds(10000))
                    .header("accept", "application/json")
                    .uri(URI.create("https://escaperoom-game.herokuapp.com/players"))
                    .build();
            client.send(request, HttpResponse.BodyHandlers.ofString());
            log.debug("Request sent");
        } catch (InterruptedException | IOException e) {
            log.fatal("httpGET Request has failed: \n" + e.getMessage() + "\n");
            e.printStackTrace();
        }
    }
}
