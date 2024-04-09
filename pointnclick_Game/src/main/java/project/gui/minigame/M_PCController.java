package project.gui.minigame;

import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.util.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import project.game.player.Player;
import project.gui.SuperController;
import project.audio.SoundPlayer;
import project.game.*;

import java.net.URL;
import java.util.ResourceBundle;


public class M_PCController extends SuperController implements Initializable {

    @FXML private AnchorPane container;
    @FXML private Label closeLabel, riddleText, labelKey;
    @FXML private PasswordField pwField;
    @FXML private Button enter, reset;
    private Button buttonOxygen, buttonWater;
    @FXML private ImageView greyGreen;
    @FXML private ImageView greyRed;
    private Player player;
    private InventoryBuilder inventoryBuilder;
    private int superCount = 0;
    private SoundPlayer soundPlayer;
    private ResourceBundle resourceBundle;

    private static final Logger log = LogManager.getLogger(M_PCController.class);


    public M_PCController(Player player, InventoryBuilder inventoryBuilder, SoundPlayer soundPlayer) {
        this.player = player;
        this.inventoryBuilder = inventoryBuilder;
        this.soundPlayer = soundPlayer;
    }

    /**
     * This Method reset the user input (if user click on reset button)
     */
    public void reset() {
        soundPlayer.playSoundEffect("M_PC_click");
        pwField.setText("");
        pwField.setStyle("-fx-border-style: none; -fx-border-width: 0px");
    }

    /**
     * Remove items from actual scene
     *
     * @param content is the object that should be remove
     */
    private void removeItems(Object content) {
        container.getChildren().remove(content);
        log.debug("removed Item: " + content);
    }

    /**
     * Set a new backgrgound image to current scene
     */
    private void setNewBG() {
        Image newBG = new Image(getClass().getResource("/img/periodictable.png").toExternalForm());
        BackgroundImage backgroundImage = new BackgroundImage(
                newBG,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        container.setBackground(background);
        log.debug("set new BG");
    }

    /**
     * Checks if the entered password is correct
     * Switch background image and remove items and read waterRiddle.txt, if it's correct
     */
    public void isCorrectPw() {
        String enteredPw = pwField.getText();
        String correctPw = "whisky123";

        if ((enteredPw.toLowerCase()).equals(correctPw)) {
            log.debug("Typed in right pw");
            soundPlayer.playSoundEffect("M_PC_click");
            pwField.setStyle("-fx-border-color: seagreen; -fx-border-width: 2px;");
            removeItems(enter);
            removeItems(reset);
            removeItems(greyGreen);
            removeItems(pwField);
            removeItems(labelKey);
            setNewBG();
            setClickableLabels();
            riddleText.setText(resourceBundle.getString("waterRiddle"));
            greyRed.setVisible(true);

        } else {
            player.increaseFailCount();
            soundPlayer.playSoundEffect("M_PC_error");
            pwField.setStyle("-fx-border-color: #8b0000; -fx-border-width: 2px;");
            pwField.setText("");
            pwField.setPromptText(resourceBundle.getString("wrongPW"));
            animation();
            greyRed.setVisible(false);
        }
    }

    private void animation() {
        ParallelTransition pTrans = new ParallelTransition();
        RotateTransition rotate = new RotateTransition(Duration.millis(60), pwField);
        rotate.setByAngle(2);
        rotate.setCycleCount(10);
        rotate.setAutoReverse(true);
        pTrans.getChildren().add(rotate);

        pTrans.play();
    }

    private void setClickableLabels() {
        Button buttonWater = new Button();
        buttonWater.setLayoutY(116);
        buttonWater.setLayoutX(127);
        buttonWater.setMaxHeight(27);
        buttonWater.setMaxWidth(27);
        buttonWater.setMinWidth(27);
        buttonWater.setMinHeight(27);
        this.buttonWater = buttonWater;
        buttonWater.setOnMouseClicked(click -> clickOnWater());

        Button buttonOxygen = new Button();
        buttonOxygen.setLayoutY(143);
        buttonOxygen.setLayoutX(555);
        buttonOxygen.setPrefWidth(27);
        buttonOxygen.setPrefHeight(27);
        buttonOxygen.setMaxHeight(27);
        buttonOxygen.setMaxWidth(27);
        buttonOxygen.setMinWidth(27);
        buttonOxygen.setMinHeight(27);
        this.buttonOxygen = buttonOxygen;
        buttonOxygen.setOnMouseClicked(click -> clickOnOxygen());
        container.getChildren().addAll(buttonOxygen, buttonWater);
    }

    private void increaseSuperCount() {
        superCount++;
    }

    private int getSuperCount() {
        return superCount;
    }

    private void clickOnWater() {
        soundPlayer.playSoundEffect("M_PC_click");
        increaseSuperCount();
        if (getSuperCount() == 2) {
            buttonWater.setGraphic(new ImageView(new Image(getClass().getResource("/img/2ndWaterClick.png").toExternalForm())));
        }
    }

    private void clickOnOxygen() {
        soundPlayer.playSoundEffect("M_PC_click");
        increaseSuperCount();
        if (getSuperCount() >= 2) {
            buttonOxygen.setStyle("-fx-border-color: seagreen;" + "-fx-border-width: 2px;");
            addWaterItemAndClose();
        }
    }

    private void addWaterItemAndClose() {
        inventoryBuilder.addItem(InventoryItems.WATER, null, container, false);
        inventoryBuilder.removeItem(InventoryItems.PW_NOTE);
        log.debug("Add item Water to inventory");
        player.setMinigameSolved(SceneSwitchItems.Minigames.PC.ordinal(), true);
        closePopUp(container);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
        closeLabel.setOnMouseClicked(mouseEvent -> closePopUp(container));
        labelKey.setText(resourceBundle.getString("labelKey"));
    }
}
