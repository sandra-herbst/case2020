package project;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    private static Logger log = LogManager.getLogger(App.class);

    public static void main(String[] args) {
        try {
            ThreadRequest request = new ThreadRequest();
            request.runSchedule();
            SpringApplication.run(App.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
