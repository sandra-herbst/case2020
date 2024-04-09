package project;

import Manage.PostgreSQL;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("ALL")
@RestController
public class RestApiController {

    private static Logger log = LogManager.getLogger(RestApiController.class);

    @GetMapping("/players")
    public String player() throws SQLException {
        ResultSet rs = null;
        try {
            PostgreSQL.connect();
            rs = PostgreSQL.getAllPlayers("player");
            JSONArray array = new JSONArray();

                while(rs.next()) {
                    JSONObject record = new JSONObject();
                    //Inserting key-value pairs into the json object
                    record.put("name", rs.getString("name"));
                    record.put("endDate", rs.getString("endDate"));
                    record.put("startDate", rs.getString("startDate"));
                    record.put("sumPlayTime", rs.getString("sumPlayTime"));
                    record.put("failCounter", rs.getInt("failCounter"));
                    array.add(record);
                }

            log.info("Highscore: " + array.toString());
            return array.toString();

        } catch (Exception e){
            return "{\"error\": \"No players in highscore\"}";
        } finally {
            rs.close();
            PostgreSQL.disconnect();
        }
    }

    @PostMapping(path = "/players", consumes = "application/json", produces = "application/json")
    public String postPlayer(@RequestBody Player player){
        try {
            PostgreSQL.connect();
            PostgreSQL.createNewPlayerEntry("player", player.getName(), player.getFailCounter(), player.getSumPlayTime(), player.getStartDate(), player.getEndDate());
            log.info("New Player: " + player);
            return "{\"success\": \"player now in highscore\"," +
                    "\"player\":" + player + "}";
        } catch (Exception e){
            e.printStackTrace();
            return "{\"error\": \"Player could not be saved in the database\"}";
        } finally {
            PostgreSQL.disconnect();
        }
    }
}
