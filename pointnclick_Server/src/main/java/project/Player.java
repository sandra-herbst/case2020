package project;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.Serializable;

public class Player implements Serializable {

    /*firstStartDate is the time when the player starts to play for the first time
    calcStartDate is the time when the player starts to play the game
    finalEndDate is the time when the player finish the game
     */
    private static final Logger log = LogManager.getLogger(Player.class);

    private String name, endDate, startDate, sumPlayTime;
    private int failCounter;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getSumPlayTime() {
        return sumPlayTime;
    }

    public void setSumPlayTime(String sumPlayTime) {
        this.sumPlayTime = sumPlayTime;
    }

    public int getFailCounter() {
        return failCounter;
    }

    public void setFailCounter(int failCounter) {
        this.failCounter = failCounter;
    }

    @Override
    public String toString() {
        return "{\"name\" = \"" + name +
                "\", \"endDate\" = \"" + endDate +
                "\", \"startDate\" = \"" + startDate+
                "\", \"sumPlayTime\" = \"" + sumPlayTime +
                "\", \"failCounter\" = \"" + failCounter +
                "\"}";
    }
}

