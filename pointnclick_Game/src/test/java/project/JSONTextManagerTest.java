package project;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import project.game.JSONTextManager;
import project.game.player.Settings;

public class JSONTextManagerTest {

    private static final Logger log = LogManager.getLogger(JSONTextManagerTest.class);

    private JSONTextManager jsonManager;

    @Before
    public void createInstance(){
        this.jsonManager = new JSONTextManager();
        jsonManager.parseJSON("test");
    }

    @Test
    public void keyExists(){
        Assert.assertTrue(jsonManager.hasKey("STARRYNIGHT"));
        Assert.assertTrue(jsonManager.hasKey("BLUESUBSTANCE"));
        Assert.assertTrue(jsonManager.hasKey("MONALISA"));
        Assert.assertTrue(jsonManager.hasKey("NOTE"));
        Assert.assertTrue(jsonManager.hasKey("NOTE2"));
        Assert.assertTrue(jsonManager.hasKey("NOTE3"));
        Assert.assertTrue(jsonManager.hasKey("NOTE4"));
        Assert.assertTrue(jsonManager.hasKey("SUBSTANCES"));

        Assert.assertFalse(jsonManager.hasKey("hdlajkdhhd"));
        Assert.assertFalse(jsonManager.hasKey("starrynight"));
        Assert.assertFalse(jsonManager.hasKey("substances"));
        Assert.assertFalse(jsonManager.hasKey("1"));
        Assert.assertFalse(jsonManager.hasKey("DrDeany"));
        Assert.assertFalse(jsonManager.hasKey("helloworld"));
    }

    @Test
    public void getTextTest() {
        Assert.assertEquals("This is a vessel with water.", jsonManager.getText("WATER", Settings.language.ENGLISH));
        Assert.assertEquals("Das ist ein Gefäß mit Wasser.", jsonManager.getText("WATER", Settings.language.GERMAN));

        Assert.assertEquals("This plant smells rotten.", jsonManager.getText("FLOWER", Settings.language.ENGLISH));
        Assert.assertEquals("Diese Pflanze riecht verfault.", jsonManager.getText("FLOWER", Settings.language.GERMAN));

        Assert.assertEquals("So...\nWill you go out with me?", jsonManager.getText("CANDLE1", Settings.language.ENGLISH));
        Assert.assertEquals("Also...\nMöchtest du mit mir ausgehen?", jsonManager.getText("CANDLE1", Settings.language.GERMAN));

        Assert.assertEquals("I don't have any time to chill!", jsonManager.getText("BIGCHAIR", Settings.language.ENGLISH));
        Assert.assertEquals("Ich hab keine Zeit zum Ausruhen!", jsonManager.getText("BIGCHAIR", Settings.language.GERMAN));

        Assert.assertEquals("Eine Notiz\n\"Denk wie ein Proton und bleib positiv.\"", jsonManager.getText("NOTE", Settings.language.GERMAN));
        Assert.assertEquals("A note\n\"Think like a proton and stay positive\"", jsonManager.getText("NOTE", Settings.language.ENGLISH));

        Assert.assertNull(jsonManager.getText("sjdpsjrkvl", Settings.language.ENGLISH));
        Assert.assertNull(jsonManager.getText("substances", Settings.language.ENGLISH));
        Assert.assertNull(jsonManager.getText("DrDeany", Settings.language.ENGLISH));
        Assert.assertNull(jsonManager.getText("helloworld", Settings.language.ENGLISH));
        Assert.assertNull(jsonManager.getText("starrynight", Settings.language.ENGLISH));
        Assert.assertNull(jsonManager.getText("1", Settings.language.ENGLISH));

        Assert.assertNull(jsonManager.getText("sjdpsjrkvl", Settings.language.GERMAN));
        Assert.assertNull(jsonManager.getText("substances", Settings.language.GERMAN));
        Assert.assertNull(jsonManager.getText("DrDeany", Settings.language.GERMAN));
        Assert.assertNull(jsonManager.getText("helloworld", Settings.language.GERMAN));
        Assert.assertNull(jsonManager.getText("starrynight", Settings.language.GERMAN));
        Assert.assertNull(jsonManager.getText("1", Settings.language.GERMAN));
    }
}
