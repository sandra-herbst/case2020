package project;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import project.game.player.Player;
import project.network.HTTPBuilder;

import java.net.http.HttpResponse;

public class HTTPBuilderTest {

    private static final Logger log = LogManager.getLogger(HTTPBuilderTest.class);

    Player player;
    private HTTPBuilder httpBuilder;

    @Before
    public void createInstance(){
        this.player = new Player();
        player.setStartDate();
        player.setEndDate();
        player.setCalcStartDate();
        this.httpBuilder = new HTTPBuilder("https://google.com");
    }

    @Test
    public void getData(){
        HttpResponse<String> response = httpBuilder.httpGET();
        log.debug(response);
    }

    @Test
    public void postData(){
        HttpResponse<String> response = httpBuilder.httpPOST(player);
        log.debug(response);
    }
}
