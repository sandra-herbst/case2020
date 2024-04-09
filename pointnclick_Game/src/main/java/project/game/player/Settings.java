package project.game.player;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This class keeps track of the configurable settings in the game by enum constants
 * of language and theme.
 */
public class Settings {

    // English Bundle
    private static final Locale localeEN = new Locale ("en" , "UK");
    private static final ResourceBundle bundleEN = ResourceBundle.getBundle("language.Language_en",localeEN);
    // German Bundle
    private static final Locale localeDE = new Locale ("de" , "DE");
    private static final ResourceBundle bundleDE = ResourceBundle.getBundle("language.Language_de",localeDE);

    public enum language {
        GERMAN(bundleDE), ENGLISH(bundleEN);

        private final ResourceBundle bundle;

        language(ResourceBundle bundle) {
            this.bundle = bundle;
        }

        public ResourceBundle getBundle() {
            return bundle;
        }
    }

    public enum theme {
        LIGHT("/css/LightTheme.css") , DARK("/css/DarkTheme.css");

        private final String url;

        theme(String url) {
            this.url = url;
        }

        public String getURL(){
            return url;
        }
    }
}
