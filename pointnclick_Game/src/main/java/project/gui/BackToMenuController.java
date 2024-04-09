package project.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import project.audio.SoundPlayer;
import project.game.SceneSwitchItems;
import project.game.SuperLevel;
import project.game.player.Player;

import java.net.URL;
import java.util.ResourceBundle;

public class BackToMenuController extends SuperController implements Initializable {

    private SoundPlayer soundPlayer;
    @FXML
    private AnchorPane container;

    @FXML
    private Label close, toMenu;

    private static final Logger log = LogManager.getLogger(BackToMenuController.class);


    public BackToMenuController(SoundPlayer soundPlayer) {
        this.soundPlayer = soundPlayer;
    }

    /**
     * this method provides two event handlers to deal with the user input
     * #close closes popup
     * #toMenu loads start screen and additionally, the old player progress (by creating a new ApplicationContext)
     * progress, which has not been saved before, is lost
     */
    private void setButtons(){
        close.setOnMouseClicked(mouseEvent -> {
            closePopUp(container);
        });


        toMenu.setOnMouseClicked(mouseEvent -> {
            ApplicationContext context = new ClassPathXmlApplicationContext("file:src/main/resources/spring/spring.xml");
            Player player = (Player) context.getBean("player");
            Main.setContext(context);

            soundPlayer.stopBackgroundSound();
            AnchorPane ownerPane = (AnchorPane)((Stage) container.getScene().getWindow()).getOwner().getScene().getRoot();
            closePopUp(container);
            //load old progress
            loadNextScene(SceneSwitchItems.Scenes.MAIN.getFXMLURL(),ownerPane,player);
        });
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setButtons();
    }
}
