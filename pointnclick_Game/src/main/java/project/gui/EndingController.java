package project.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import project.audio.SoundPlayer;
import project.game.GameState;
import project.game.SceneSwitchItems;
import project.game.player.Player;
import project.network.HTTPBuilder;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EndingController extends SuperController implements Initializable {

    private SoundPlayer soundPlayer;
    @FXML private AnchorPane container;
    @FXML Label closeLabelEnding, STATS, STATS2, CREDITS;
    private Player player;

    private static final Logger log = LogManager.getLogger(SuperController.class);

    public EndingController(Player player, SoundPlayer soundPlayer) {
        this.player = player;
        this.soundPlayer = soundPlayer;
    }

    public void postPlayerData() {
        HTTPBuilder builder = new HTTPBuilder("https://escaperoom-game.herokuapp.com/players");
        builder.httpPOST(player);
        log.debug("Player Data has been sent to the database");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        Platform.runLater(() -> {
            Stage stage = (Stage) container.getScene().getWindow();
            AnchorPane ownerPane = (AnchorPane) stage.getOwner().getScene().getRoot();

            stage.setOnHidden(m -> {
                soundPlayer.stopBackgroundSound();
                loadNextScene(SceneSwitchItems.Scenes.MAIN.getFXMLURL(), ownerPane, player);
            });
        });

        try {
            player.setEndDate();
            GameState.saveGame(player, "playerData");
        } catch (IOException e) {
            e.printStackTrace();
        }

        closeLabelEnding.setOnMouseClicked(mouseEvent -> closePopUp(container));

        STATS.setText(resourceBundle.getString("stats") + "\n" + player.getSumPlayTimeAsTimeString());
        STATS2.setText(resourceBundle.getString("stats2") + "\n" + player.getFailCounter());
        CREDITS.setText(resourceBundle.getString("credit"));
        postPlayerData();
    }
}
