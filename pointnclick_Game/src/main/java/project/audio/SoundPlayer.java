package project.audio;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import project.game.player.Player;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * JavaFX MediaPlayer initialization
 * This class will play any BackgroundSound-(Looped)
 * and any SoundEffect-(Not Looped) in the players
 * preferred volume.
 */
public class SoundPlayer {

    private static final Logger log = LogManager.getLogger(SoundPlayer.class);

    private Player player;
    private MediaPlayer bgSound;

    public SoundPlayer(Player player) {
        this.player = player;
    }

    /**
     * This method will play a background sound from a given filename - loops indefinitely
     * @param filename of resource file.
     */
    public void playBackgroundSound(String filename) {
        try {
            Media media = new Media(getClass().getResource("/sound/" + filename + ".wav").toString());
            bgSound = createDefaultMediaPlayer(media, true);
            bgSound.play();

        } catch (Exception e){
            log.fatal("Couldn't play background music.");
            e.printStackTrace();
        }
    }

    /**
     * This method will play a background sound from a given filename (without .mp3 extension) - doesn't loop
     * @param filename of resource file.
     * @return initialized MediaPlayer to stop a soundEffect from playing if needed.
     */
    public MediaPlayer playSoundEffect(String filename) {
        try {
            Media media = new Media(getClass().getResource("/sound/" + filename + ".wav").toString());

            MediaPlayer mediaPlayer = createDefaultMediaPlayer(media, false);
            mediaPlayer.play();
            return mediaPlayer;

        } catch (Exception e){
            log.fatal("Couldn't play sound effect.");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This method includes code that would otherwise repeat itself.
     * @param media that is to be played.
     * @param isLooped if sound has to loop endlessly.
     * @return MediaPlayer object.
     */
    private MediaPlayer createDefaultMediaPlayer(Media media, boolean isLooped){
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        if (isLooped) {
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.setVolume(player.getBGSoundVolume());
        } else {
            mediaPlayer.setVolume(player.getEffectSoundVolume());
        }

        mediaPlayer.setAutoPlay(true);
        return mediaPlayer;
    }

    public void stopBackgroundSound(){
        bgSound.stop();
    }

    public void updateVolumeSettings(){
        bgSound.setVolume(player.getBGSoundVolume());
    }

    public MediaPlayer.Status getStatus(){
        try {
            return bgSound.getStatus();
        } catch (Exception e){
            return MediaPlayer.Status.UNKNOWN;
        }
    }
}
