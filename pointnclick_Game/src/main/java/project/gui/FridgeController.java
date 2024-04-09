package project.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import project.game.InventoryBuilder;
import project.game.SceneSwitchItems;
import project.game.SuperLevel;
import project.game.player.Player;

import java.net.URL;
import java.util.ResourceBundle;

public class FridgeController extends SuperLevel implements Initializable {
    @FXML
    private ImageView WHISKEY;

    @FXML
    private AnchorPane container;

    @FXML private Label closeLabel;

    private Player player;
    private InventoryBuilder inventory;

    public FridgeController(Player player, InventoryBuilder inventoryBuilder) {
        super(player , inventoryBuilder, null, null, 0);
        this.inventory = inventoryBuilder;
        this.player = player;
    }

    /**
     * checks if player has already solved the 4Image minigame and if so,
     * removes the click listener. If not, the player can start the minigame by clicking
     * on #WHISKEY
     */
    private void setUpGUIPlayerProgress(){
        reloadPlayerInventoryProgress();
        if (player.getMinigameSolved()[SceneSwitchItems.Minigames.DOOR.ordinal()]){
            removeMinigameClickEvent(WHISKEY);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setContainer(container);
        closeLabel.setOnMouseClicked(mouseEvent -> closePopUp(container));
        setUpGUIPlayerProgress();
        setAllMinigameItems(new ImageView[]{WHISKEY});

    }
}
