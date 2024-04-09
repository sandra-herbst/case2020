package project;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import project.game.player.*;

public class PlayerProgressManagerTest {

    private Player player;
    private PlayerProgressManager progressManager;

    @Before
    public void createInstance(){
        this.progressManager = new PlayerProgressManager();
        this.player = new Player(new SettingsManager(), progressManager, new FailCountProperty());
    }

    @Test
    public void playerHasItems(){
        player.setHasInventoryItem(0, true);
        player.setHasInventoryItem(1, true);
        player.setHasInventoryItem(2, true);
        player.setHasInventoryItem(3, true);
        player.setHasInventoryItem(4, true);
        player.setHasInventoryItem(5, true);
        player.setHasInventoryItem(6, true);
        player.setHasInventoryItem(7, true);

        Assert.assertEquals(player.getHasInventoryItemBooleans()[0][0], progressManager.getHasItem()[0][0]);
        Assert.assertEquals(player.getHasInventoryItemBooleans()[1][0], progressManager.getHasItem()[1][0]);
        Assert.assertEquals(player.getHasInventoryItemBooleans()[2][0], progressManager.getHasItem()[2][0]);
        Assert.assertEquals(player.getHasInventoryItemBooleans()[3][0], progressManager.getHasItem()[3][0]);
        Assert.assertEquals(player.getHasInventoryItemBooleans()[4][0], progressManager.getHasItem()[4][0]);
        Assert.assertEquals(player.getHasInventoryItemBooleans()[5][0], progressManager.getHasItem()[5][0]);
        Assert.assertEquals(player.getHasInventoryItemBooleans()[6][0], progressManager.getHasItem()[6][0]);
        Assert.assertEquals(player.getHasInventoryItemBooleans()[7][0], progressManager.getHasItem()[7][0]);

        Assert.assertEquals(player.getHasInventoryItemBooleans()[8][0], progressManager.getHasItem()[8][0]);
        Assert.assertEquals(player.getHasInventoryItemBooleans()[9][0], progressManager.getHasItem()[9][0]);
        Assert.assertEquals(player.getHasInventoryItemBooleans()[10][0], progressManager.getHasItem()[10][0]);
    }

    @Test
    public void playerSolvedMinigames(){
        player.setMinigameSolved(0, true);
        player.setMinigameSolved(1, true);
        player.setMinigameSolved(2, true);
        player.setMinigameSolved(3, true);
        player.setMinigameSolved(4, true);

        Assert.assertEquals(player.getMinigameSolved()[0], progressManager.getSolvedMinigame()[0]);
        Assert.assertEquals(player.getMinigameSolved()[1], progressManager.getSolvedMinigame()[1]);
        Assert.assertEquals(player.getMinigameSolved()[2], progressManager.getSolvedMinigame()[2]);
        Assert.assertEquals(player.getMinigameSolved()[3], progressManager.getSolvedMinigame()[3]);
        Assert.assertEquals(player.getMinigameSolved()[4], progressManager.getSolvedMinigame()[4]);
        Assert.assertEquals(player.getMinigameSolved()[5], progressManager.getSolvedMinigame()[5]);
    }

    @Test
    public void playerItemNumber(){
        player.increaseItemInventoryNumber(2);
        Assert.assertEquals(2, progressManager.getItemNumber());

        player.increaseItemInventoryNumber(1);
        Assert.assertEquals(3, progressManager.getItemNumber());

        player.increaseItemInventoryNumber(10);
        Assert.assertEquals(13, progressManager.getItemNumber());

        for (int i = 14; i < 25; i++){
            player.increaseItemInventoryNumber(1);
            Assert.assertEquals(i, progressManager.getItemNumber());
        }

        player.increaseItemInventoryNumber(-1);
        Assert.assertEquals(23, progressManager.getItemNumber());

        player.increaseItemInventoryNumber(-3);
        Assert.assertEquals(20, progressManager.getItemNumber());

        player.increaseItemInventoryNumber(-2);
        Assert.assertEquals(18, progressManager.getItemNumber());

        for (int i = 17; 5 < i; i--){
            player.increaseItemInventoryNumber(-1);
            Assert.assertEquals(i, progressManager.getItemNumber());
        }
    }
}
