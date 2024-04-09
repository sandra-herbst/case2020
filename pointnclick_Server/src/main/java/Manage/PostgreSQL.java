package Manage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.time.Duration;

public class PostgreSQL {

    private static Connection conn;
    private static Statement stmt;
    private static Logger log = LogManager.getLogger(PostgreSQL.class);

    public static void connect(){
        conn = null;

        try{
            URI dbUri = new URI(System.getenv("DATABASE_URL"));

            String username = dbUri.getUserInfo().split(":")[0];
            String password = dbUri.getUserInfo().split(":")[1];
            String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
            // Java Database Connectivity
            conn = DriverManager.getConnection(dbUrl, username, password);
            System.out.println("Connection to database successful.");
            stmt = conn.createStatement();

        } catch (SQLException | URISyntaxException e){
            e.printStackTrace();
        }

    }

    public static void disconnect(){
        try{
            if (conn != null){
                conn.close();

                System.out.println("Disconnection to database successful.");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static Connection getConn() {
        return conn;
    }

    public static void onUpdate(String sql){
        try{
            stmt.execute(sql);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static ResultSet onQuery(String sql){
        try {
            return conn.prepareStatement(sql).executeQuery();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Create entirely new entries for players who have finished the game
     */
    public static void createNewPlayerEntry(String table, String username, int failCounter, String sumPlayTime, String startDate, String endDate){
        Duration time = Duration.parse(sumPlayTime);
        long totalMillisec = time.toMillis() + (failCounter * 60 * 1000);
        onUpdate(String.format("INSERT INTO %s (name, failCounter, sumPlayTime, startDate, endDate, totalMillisec) VALUES('%s' , '%s', '%s', '%s', '%s', '%s')", table, username, failCounter, sumPlayTime, startDate, endDate, totalMillisec));
    }

   /**
     * Get top x players of the highscore list
     * @param table name of database table
     */
    public static ResultSet getAllPlayers(String table) {
        return onQuery(String.format("SELECT * FROM %s ORDER BY totalMillisec ASC LIMIT 10", table));
    }
}
