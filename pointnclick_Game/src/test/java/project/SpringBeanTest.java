package project;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import project.audio.SoundPlayer;
import project.gui.*;
import project.gui.minigame.M_TidyUpController;
import project.game.InventoryBuilder;
import project.game.player.Player;
import project.game.PlayerFactory;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

public class SpringBeanTest {

   private M_TidyUpController m_tidyUpController;
   private InventoryBuilder inventoryBuilder;
   private MainController mainController;
   private SoundPlayer soundPlayer;
   private ComputerRoomController computerRoomController;
   private EmailController emailController;
   private LaboratoryController laboratoryController;
   private FridgeController fridgeController;
   private LivingRoomController livingRoomController;
   private HighscoreController highscoreController;

   private final ApplicationContext context = new ClassPathXmlApplicationContext("file:src/main/resources/spring/spring.xml");

    @Before
    public void createBeans() {
        m_tidyUpController = (M_TidyUpController) context.getBean("m_tidyup");
        inventoryBuilder = (InventoryBuilder) context.getBean("inventoryBuilder");
        mainController = (MainController) context.getBean("mainController");
        soundPlayer = (SoundPlayer) context.getBean("soundPlayer");
        computerRoomController = (ComputerRoomController) context.getBean("computerRoomController");
        emailController = (EmailController) context.getBean("emailController");
        laboratoryController = (LaboratoryController) context.getBean("laboratoryController");
        fridgeController = (FridgeController) context.getBean("fridgeController");
        livingRoomController = (LivingRoomController) context.getBean("livingRoomController");
        highscoreController = (HighscoreController) context.getBean("highscoreController");
    }

    @Test
    public void givenBean__instanceOfExpectedClass__thenCorrect() {
        Assert.assertThat(inventoryBuilder,isA(InventoryBuilder.class));
        Assert.assertThat(m_tidyUpController, isA(M_TidyUpController.class));
        Assert.assertThat(mainController, isA(MainController.class));
        Assert.assertThat(soundPlayer, isA(SoundPlayer.class));
        Assert.assertThat(computerRoomController, isA(ComputerRoomController.class));
        Assert.assertThat(emailController, isA(EmailController.class));
        Assert.assertThat(laboratoryController, isA(LaboratoryController.class));
        Assert.assertThat(fridgeController, isA(FridgeController.class));
        Assert.assertThat(livingRoomController, isA(LivingRoomController.class));
        Assert.assertThat(highscoreController, isA(HighscoreController.class));
   }
}





